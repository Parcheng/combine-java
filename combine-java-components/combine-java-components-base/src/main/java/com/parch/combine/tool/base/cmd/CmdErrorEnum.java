package com.parch.combine.tool.base.cmd;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 异常枚举
 */
public enum CmdErrorEnum implements IComponentError {

    FAIL("执行失败", "执行失败"),
    ;

    private String msg;

    private String showMsg;

    CmdErrorEnum(String msg, String showMsg) {
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
