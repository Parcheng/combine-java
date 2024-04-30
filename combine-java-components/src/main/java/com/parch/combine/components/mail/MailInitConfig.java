package com.parch.combine.components.mail;

import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 初始化配置类
 */
public class MailInitConfig extends InitConfig {

    @ComponentField(key = "host", name = "服务主机", type = FieldTypeEnum.TEXT, isRequired = true)
    private String host;

    @ComponentField(key = "port", name = "端口", type = FieldTypeEnum.NUMBER, isRequired = true)
    private Integer port;

    @ComponentField(key = "mail", name = "发件方邮箱", type = FieldTypeEnum.TEXT, isRequired = true)
    private String mail;

    @ComponentField(key = "authCode", name = "授权码", type = FieldTypeEnum.TEXT, isRequired = true)
    private String authCode;

    @ComponentField(key = "timeout", name = "超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "10000")
    private Long timeout;

    @ComponentField(key = "debug", name = "是否打印日志", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean debug;

    @Override
    public void init() {
        if (getTimeout() == null) {
            setTimeout(10000L);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }
}
