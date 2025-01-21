package com.parch.combine.logic.base.module;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 异常枚举
 */
public enum LogicModuleErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    LogicModuleErrorEnum(String msg, String showMsg) {
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
