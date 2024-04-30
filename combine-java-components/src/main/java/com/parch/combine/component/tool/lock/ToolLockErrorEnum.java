package com.parch.combine.component.tool.lock;

import com.parch.combine.core.error.IComponentError;

/**
 * 数据替换错误信息枚举
 */
public enum ToolLockErrorEnum implements IComponentError {

    FAIL("系统异常", "系统错误"),
    ;

    private String msg;

    private String showMsg;

    ToolLockErrorEnum(String msg, String showMsg) {
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
