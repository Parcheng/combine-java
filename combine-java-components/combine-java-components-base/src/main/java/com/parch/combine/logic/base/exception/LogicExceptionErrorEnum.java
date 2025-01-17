package com.parch.combine.logic.base.exception;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 异常枚举
 */
public enum LogicExceptionErrorEnum implements IComponentError {

    SYSTEM_ERROR("系统异常", "系统异常");

    private String msg;

    private String showMsg;

    LogicExceptionErrorEnum(String msg, String showMsg) {
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
