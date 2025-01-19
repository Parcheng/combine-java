package com.parch.combine.logic.base.group;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 异常枚举
 */
public enum LogicGroupErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    LogicGroupErrorEnum(String msg, String showMsg) {
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
