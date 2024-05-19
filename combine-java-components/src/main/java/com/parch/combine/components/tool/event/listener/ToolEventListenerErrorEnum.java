package com.parch.combine.components.tool.event.listener;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum ToolEventListenerErrorEnum implements IComponentError {

    FAIL("注解事件监听异常", "注解事件监听失败");

    private String msg;

    private String showMsg;

    ToolEventListenerErrorEnum(String msg, String showMsg) {
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
