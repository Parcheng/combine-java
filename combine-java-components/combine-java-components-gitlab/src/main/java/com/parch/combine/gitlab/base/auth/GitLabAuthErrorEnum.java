package com.parch.combine.gitlab.base.auth;

import com.parch.combine.core.component.error.IComponentError;

public enum GitLabAuthErrorEnum implements IComponentError {
    NO_AUTH_CACHE("鉴权信息不存在", "鉴权信息不存在"),
    CLEAR_FAIL("清理失败", "清理失败"),
    AUTH_FAIL("鉴权失败", "鉴权失败"),
    ;

    private String msg;

    private String showMsg;

    GitLabAuthErrorEnum(String msg, String showMsg) {
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
