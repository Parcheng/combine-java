package com.parch.combine.component.tool.crontab;

import com.parch.combine.core.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum SystemCrontabErrorEnum implements IComponentError {

    FAIL("创建定时任务异常", "定时任务创建失败");

    private String msg;

    private String showMsg;

    SystemCrontabErrorEnum(String msg, String showMsg) {
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
