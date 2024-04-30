package com.parch.combine.component.mail.receive;

import com.parch.combine.core.error.IComponentError;

public enum MailReceiveErrorEnum implements IComponentError {

    ADDRESS_ERROR("邮件接收失败", "邮箱地址错误"),
    FAIL("邮件接收失败", "邮件接收失败"),
    ;

    private String msg;

    private String showMsg;

    MailReceiveErrorEnum(String msg, String showMsg) {
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
