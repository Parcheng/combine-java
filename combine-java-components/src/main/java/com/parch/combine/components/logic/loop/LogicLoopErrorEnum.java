package com.parch.combine.components.logic.loop;

import com.parch.combine.core.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum LogicLoopErrorEnum implements IComponentError {

    DATA_ERROR("来源数据非集合类型数据", "数据异常"),
    ;

    private String msg;

    private String showMsg;

    LogicLoopErrorEnum(String msg, String showMsg) {
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
