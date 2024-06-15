package com.parch.combine.tool.base.sleep;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 数据替换错误信息枚举
 */
public enum ToolSleepErrorEnum implements IComponentError {

    FAIL("休眠失败", "系统错误"),
    ;

    private String msg;

    private String showMsg;

    ToolSleepErrorEnum(String msg, String showMsg) {
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
