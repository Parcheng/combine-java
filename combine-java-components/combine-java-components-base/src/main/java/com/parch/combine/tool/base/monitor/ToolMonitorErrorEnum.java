package com.parch.combine.tool.base.monitor;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum ToolMonitorErrorEnum implements IComponentError {

    FAIL("系统监听异常", "系统监听失败");

    private String msg;

    private String showMsg;

    ToolMonitorErrorEnum(String msg, String showMsg) {
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
