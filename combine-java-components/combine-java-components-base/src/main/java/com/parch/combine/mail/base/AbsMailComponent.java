package com.parch.combine.mail.base;

import com.parch.combine.core.component.base.AbsComponent;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

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

        props.put("mail.smtp.host", initConfig.host());
        props.put("mail.smtp.port", initConfig.port());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Long timeout = initConfig.timeout();

        // 与邮件服务器建立连接的时间限制
        props.setProperty("mail.smtp.connectiontimeout", timeout.toString());
        // 邮件smtp读取的时间限制
        props.setProperty("mail.smtp.timeout", timeout.toString());
        // 邮件内容上传的时间限制
        props.setProperty("mail.smtp.writetimeout", timeout.toString());

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(initConfig.mail(), initConfig.authCode());
            }
        });

        Boolean debug = initConfig.debug();
        session.setDebug(debug != null && debug);
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

        props.put("mail.imap.host", initConfig.host());
        props.put("mail.imap.port", initConfig.port());
        props.put("mail.imap.auth", "true");
        props.put("mail.imap.starttls.enable", "true");
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props);

        Boolean debug = initConfig.debug();
        session.setDebug(debug != null && debug);
        Store store = session.getStore("imap");
        store.connect(initConfig.mail(), initConfig.authCode());

        return store;
    }
}
