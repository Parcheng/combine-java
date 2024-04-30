package com.parch.combine.components.mail;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;

import javax.mail.*;
import java.util.*;

/**
 * 邮件组件
 */
public abstract class AbsMailComponent<T extends MailInitConfig, R extends MailLogicConfig> extends AbsComponent<T, R> {

    /**
     * 构造器
     */
    public AbsMailComponent(Class<T> tClass, Class<R> rClass) {
        super(tClass, rClass);
    }

    @Override
    public List<String> init() {
        MailInitConfig initConfig = getInitConfig();
        List<String> errorMsg = new ArrayList<>();

        if (CheckEmptyUtil.isEmpty(initConfig.getMail())) {
            errorMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "邮箱账户为空"));
        }
        if (CheckEmptyUtil.isEmpty(initConfig.getAuthCode())) {
            errorMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "邮箱账户密码为空"));
        }
        if (CheckEmptyUtil.isEmpty(initConfig.getHost())) {
            errorMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "邮箱服务器HOST为空"));
        }
        if (initConfig.getPort() == null) {
            errorMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "邮箱服务器端口为空"));
        }

        errorMsg.addAll(checkMailConfig());
        return errorMsg;
    }

    protected abstract List<String> checkMailConfig();

    /**
     * 获取发件Session
     *
     * @return Session
     */
    protected Session getSendSession() {
        MailInitConfig initConfig = getInitConfig();

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        props.put("mail.smtp.host", initConfig.getHost());
        props.put("mail.smtp.port", initConfig.getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // 与邮件服务器建立连接的时间限制
        props.setProperty("mail.smtp.connectiontimeout", initConfig.getTimeout().toString());
        // 邮件smtp读取的时间限制
        props.setProperty("mail.smtp.timeout", initConfig.getTimeout().toString());
        // 邮件内容上传的时间限制
        props.setProperty("mail.smtp.writetimeout", initConfig.getTimeout().toString());

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(initConfig.getMail(), initConfig.getAuthCode());
            }
        });
        session.setDebug(initConfig.getDebug() != null && initConfig.getDebug());
        return session;
    }

    /**
     * 获取收件Session
     *
     * @return Session
     */
    protected Store getReceiveSession() throws MessagingException {
        MailInitConfig initConfig = getInitConfig();

        Properties props = new Properties();
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.ssl.protocols", "TLSv1.2");

        props.put("mail.imap.host", initConfig.getHost());
        props.put("mail.imap.port", initConfig.getPort());
        props.put("mail.imap.auth", "true");
        props.put("mail.imap.starttls.enable", "true");
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(initConfig.getDebug() != null && initConfig.getDebug());
        Store store = session.getStore("imap");
        store.connect(initConfig.getMail(), initConfig.getAuthCode());

        return store;
    }
}
