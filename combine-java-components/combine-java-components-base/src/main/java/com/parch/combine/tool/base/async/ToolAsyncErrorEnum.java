package com.parch.combine.tool.base.async;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum ToolAsyncErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    ToolAsyncErrorEnum(String msg, String showMsg) {
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
