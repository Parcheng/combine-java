package com.parch.combine.components.mail.send;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.mail.AbsMailComponent;
import com.parch.combine.components.mail.helper.MimeMessageHelper;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 运算组件
 */
@Component(key = "send", name = "邮件发送组件", logicConfigClass = MailSendLogicConfig.class, initConfigClass = MailSendInitConfig.class)
@ComponentResult(name = "邮件发送成功：true")
public class MailSendComponent extends AbsMailComponent<MailSendInitConfig, MailSendLogicConfig> {

    /**
     * 构造器
     */
    public MailSendComponent() {
        super(MailSendInitConfig.class, MailSendLogicConfig.class);
    }


    @Override
    protected List<String> checkMailConfig() {
        MailSendLogicConfig logicConfig = getLogicConfig();
        List<String> errorMsg = new ArrayList<>();

        if (CheckEmptyUtil.isEmpty(logicConfig.getTo())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "目标邮箱地址为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getSubject())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "标题为空"));
        }

        return errorMsg;
    }

    @Override
    protected DataResult execute() {
        MailSendInitConfig initConfig = getInitConfig();
        MailSendLogicConfig logicConfig = getLogicConfig();
        Session session = getSendSession();

        InternetAddress from;
        List<InternetAddress> toList = new ArrayList<>();
        try {
            from = new InternetAddress(initConfig.getMail());
            for (String to : logicConfig.getTo()) {
                toList.add(new InternetAddress(to));
            }
        } catch (MessagingException e) {
            ComponentErrorHandler.print(MailSendErrorEnum.ADDRESS_ERROR, e);
            return DataResult.fail(MailSendErrorEnum.ADDRESS_ERROR);
        }

        try {
            Multipart multipart = new MimeMultipart();
            for (MailSendLogicConfig.MailBody item : logicConfig.getBody()) {
                multipart.addBodyPart(this.buildBody(item));
            }

            // 消息
            Message message = new MimeMessage(session);
            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, toList.toArray(new InternetAddress[0]));
            message.setSubject(logicConfig.getSubject());
            message.setContent(multipart);
            message.setSentDate(new Date());

            Transport.send(message);
        } catch (Exception e) {
            ComponentErrorHandler.print(MailSendErrorEnum.FAIL, e);
            return DataResult.fail(MailSendErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    /**
     * 构建消息体
     *
     * @param item 配置项目
     * @return 消息体对象
     * @throws Exception 异常
     */
    private MimeBodyPart buildBody(MailSendLogicConfig.MailBody item) throws Exception {
        MimeBodyPart part = new MimeBodyPart();;
        Object path, content;
        switch (item.getType()) {
            case IMAGE:
                if (CheckEmptyUtil.isEmpty(item.getPath())) {
                    path = DataVariableHelper.parseValue(item.getPath(),false);
                    if (path != null) {
                        MimeMessageHelper.addImage(part, path.toString());
                    }
                } else {
                    content = DataVariableHelper.parseValue(item.getContent(),false);
                    if (content instanceof byte[]) {
                        MimeMessageHelper.addImage(part, (byte[]) content, item.getFileType());
                    }
                }
                break;
            case FILE:
                content = DataVariableHelper.parseValue(item.getContent(),false);
                if (content != null) {
                    MimeMessageHelper.addAttachment(part, content instanceof byte[] ? (byte[]) content : content.toString().getBytes(), item.getFileName());
                }
                break;
            default:
                content = DataVariableHelper.parseValue(item.getContent(),false);
                part.setText(content == null ? CheckEmptyUtil.EMPTY : content.toString());
                break;
        }

        return part;
    }
}
