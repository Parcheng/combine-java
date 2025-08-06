package com.parch.combine.security.base;

import com.parch.combine.core.component.error.IComponentError;

public enum SecurityErrorEnum implements IComponentError {
    KEY_MUST_16("秘钥必须为16位", "秘钥必须为16位"),
    NONSUPPORT_HASH_DIGITS("不支持的HASH位数", "不支持的HASH位数"),
    FAIL("失败", "失败"),
    ;

    private String msg;

    private String showMsg;

    SecurityErrorEnum(String msg, String showMsg) {
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
