package com.parch.combine.components.logic.error;

import com.parch.combine.core.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum LogicErrorErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    LogicErrorErrorEnum(String msg, String showMsg) {
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
