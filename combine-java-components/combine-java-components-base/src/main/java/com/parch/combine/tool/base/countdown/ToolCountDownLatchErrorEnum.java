package com.parch.combine.tool.base.countdown;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum ToolCountDownLatchErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    ToolCountDownLatchErrorEnum(String msg, String showMsg) {
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
