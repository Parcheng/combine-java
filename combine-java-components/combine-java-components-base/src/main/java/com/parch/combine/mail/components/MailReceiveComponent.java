package com.parch.combine.mail.components;

import com.parch.combine.mail.base.AbstractMailComponent;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.mail.base.receive.MailReceiveErrorEnum;
import com.parch.combine.mail.base.receive.MailReceiveInitConfig;
import com.parch.combine.mail.base.receive.MailReceiveLogicConfig;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(key = "receive", name = "邮件收取组件", logicConfigClass = MailReceiveLogicConfig.class, initConfigClass = MailReceiveInitConfig.class)
@ComponentDesc("依赖 mail，推荐版本 1.4.7")
@ComponentResult(name = "获取的邮件列表")
public class MailReceiveComponent extends AbstractMailComponent<MailReceiveInitConfig, MailReceiveLogicConfig> {

    public MailReceiveComponent() {
        super(MailReceiveInitConfig.class, MailReceiveLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        // MailReceiveLogicConfig logicConfig = getLogicConfig();
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            Store store = getReceiveSession();
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            for (Message message : messages) {
                Map<String, Object> msgResult = new HashMap<>(16);
                msgResult.put("subject", message.getSubject());
                msgResult.put("from", message.getFrom());
                msgResult.put("to", message.getAllRecipients());
                msgResult.put("time", message.getSentDate());

                Object content = message.getContent();
                if (content instanceof Multipart) {
                    List<Map<String, Object>> contentDataList = new ArrayList<>();
                    Multipart multiPart = (Multipart) content;
                    for (int i = 0; i < multiPart.getCount(); i++) {
                        contentDataList.add(getContentData(multiPart.getBodyPart(i)));
                    }
                    msgResult.put("content", contentDataList);
                } else {
                    Object s = message.getContent();
                    msgResult.put("content", message.getContent().toString());
                }

                result.add(msgResult);
            }

            // 关闭连接
            folder.close(false);
            store.close();

        } catch (Exception e) {
            ComponentErrorHandler.print(MailReceiveErrorEnum.FAIL, e);
            return ComponentDataResult.fail(MailReceiveErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }


    /**
     * 获取内容数据
     *
     * @param bodyPart 消息体
     * @return 消息体对象
     * @throws Exception 异常
     */
    private Map<String, Object> getContentData(BodyPart bodyPart) throws Exception {
        Map<String, Object> contentData = new HashMap<>(16);
        String contentType = bodyPart.getContentType().toLowerCase();
        if (contentType.startsWith("text/html")) {
            contentData.put("type", "html");
            contentData.put("content", bodyPart.getContent());
        } else if (bodyPart instanceof MimeBodyPart) {
            MimeBodyPart mimeBodyPart = (MimeBodyPart) bodyPart;
            String fileName = mimeBodyPart.getFileName();
            if (CheckEmptyUtil.isNotEmpty(fileName)) {
                contentData.put("name", MimeUtility.decodeText(fileName));
            }

            if (Part.ATTACHMENT.equalsIgnoreCase(mimeBodyPart.getDisposition())
                    || (mimeBodyPart.getFileName() != null && !mimeBodyPart.getFileName().isEmpty())) {
                contentData.put("type", "file");
                contentData.put("content", getBytes(mimeBodyPart));
            } else if (contentType.startsWith("image/")) {
                contentData.put("type", "image");
                contentData.put("content", getBytes(mimeBodyPart));
            } else {
                contentData.put("type", "text");
                contentData.put("content", bodyPart.getContent());
            }
        }

        return contentData;
    }

    /**
     * 获取Byte数据
     *
     * @param mimeBodyPart 消息体
     * @return 字节数据
     * @throws Exception 异常
     */
    private byte[] getBytes(MimeBodyPart mimeBodyPart) throws Exception{
        InputStream in = mimeBodyPart.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        int bytesRead;
        for (byte[] buffer = new byte[4096]; (bytesRead = in.read(buffer)) != -1;) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
        byte[] bytes = out.toByteArray();
        out.close();
        return bytes;
    }

}
