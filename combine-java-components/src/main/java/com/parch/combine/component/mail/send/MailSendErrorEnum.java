package com.parch.combine.component.mail.send;

import com.parch.combine.core.error.IComponentError;

public enum MailSendErrorEnum implements IComponentError {

    ADDRESS_ERROR("邮件发送失败", "邮箱地址错误"),
    FAIL("邮件发送失败", "邮件发送失败"),
    ;

    private String msg;

    private String showMsg;

    MailSendErrorEnum(String msg, String showMsg) {
        this.msg = msg;
        this.showMsg = showMsg;

    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getShowMsg() {
        return this.showMsg;
    }
}
