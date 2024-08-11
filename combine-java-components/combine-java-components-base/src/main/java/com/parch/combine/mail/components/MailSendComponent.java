package com.parch.combine.mail.components;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.mail.base.AbstractMailComponent;
import com.parch.combine.mail.base.helper.MailContentTypeEnum;
import com.parch.combine.mail.base.helper.MimeMessageHelper;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.mail.base.send.MailSendErrorEnum;
import com.parch.combine.mail.base.send.MailSendInitConfig;
import com.parch.combine.mail.base.send.MailSendLogicConfig;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(key = "send", name = "邮件发送组件", logicConfigClass = MailSendLogicConfig.class, initConfigClass = MailSendInitConfig.class)
@ComponentDesc("依赖 poi 和 poi-ooxml，推荐版本 4.1.2")
@ComponentResult(name = "邮件发送成功：true")
public class MailSendComponent extends AbstractMailComponent<MailSendInitConfig, MailSendLogicConfig> {

    public MailSendComponent() {
        super(MailSendInitConfig.class, MailSendLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        MailSendInitConfig initConfig = getInitConfig();
        MailSendLogicConfig logicConfig = getLogicConfig();
        Session session = getSendSession();

        InternetAddress from;
        List<InternetAddress> toList = new ArrayList<>();
        try {
            from = new InternetAddress(initConfig.mail());
            for (String to : logicConfig.to()) {
                toList.add(new InternetAddress(to));
            }
        } catch (MessagingException e) {
            ComponentErrorHandler.print(MailSendErrorEnum.ADDRESS_ERROR, e);
            return ComponentDataResult.fail(MailSendErrorEnum.ADDRESS_ERROR);
        }

        try {
            Multipart multipart = new MimeMultipart();
            for (MailSendLogicConfig.MailBody item : logicConfig.body()) {
                multipart.addBodyPart(this.buildBody(item));
            }

            // 消息
            Message message = new MimeMessage(session);
            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, toList.toArray(new InternetAddress[0]));
            message.setSubject(logicConfig.subject());
            message.setContent(multipart);
            message.setSentDate(new Date());

            Transport.send(message);
        } catch (Exception e) {
            ComponentErrorHandler.print(MailSendErrorEnum.FAIL, e);
            return ComponentDataResult.fail(MailSendErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }

    /**
     * 构建消息体
     *
     * @param item 配置项目
     * @return 消息体对象
     * @throws Exception 异常
     */
    private MimeBodyPart buildBody(MailSendLogicConfig.MailBody item) throws Exception {
        MimeBodyPart part = new MimeBodyPart();

        String path = item.path();
        Object content = item.content();
        MailContentTypeEnum type = MailContentTypeEnum.get(item.type());
        switch (type) {
            case IMAGE:
                if (path != null) {
                    MimeMessageHelper.addImage(part, path);
                } else {
                    if (content instanceof byte[]) {
                        MimeMessageHelper.addImage(part, (byte[]) content, item.fileType());
                    }
                }
                break;
            case FILE:
                if (content != null) {
                    MimeMessageHelper.addAttachment(part, content instanceof byte[] ? (byte[]) content : content.toString().getBytes(), item.fileName());
                }
                break;
            default:
                part.setText(content == null ? CheckEmptyUtil.EMPTY : DataParseUtil.getString(content));
                break;
        }

        return part;
    }
}
