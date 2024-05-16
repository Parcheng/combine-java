package com.parch.combine.components.tool.crontab;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum ToolCrontabErrorEnum implements IComponentError {

    FAIL("创建定时任务异常", "定时任务创建失败");

    private String msg;

    private String showMsg;

    ToolCrontabErrorEnum(String msg, String showMsg) {
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
