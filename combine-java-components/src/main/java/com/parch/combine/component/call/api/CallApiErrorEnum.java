package com.parch.combine.component.call.api;

import com.parch.combine.core.error.IComponentError;

public enum CallApiErrorEnum implements IComponentError {

    FAIL("调用流程失败", "调用流程失败"),
    ;

    private String msg;

    private String showMsg;

    CallApiErrorEnum(String msg, String showMsg) {
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
