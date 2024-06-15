package com.parch.combine.gui.base.build;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum GUIBuildErrorEnum implements IComponentError {

    FAIL("失败", "程序异常"),
    ;

    private String msg;

    private String showMsg;

    GUIBuildErrorEnum(String msg, String showMsg) {
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
