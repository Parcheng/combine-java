package com.parch.combine.components.gui.control;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlErrorEnum implements IComponentError {

    FAIL("失败", "控件构建失败"),
    ;

    private String msg;

    private String showMsg;

    GUIControlErrorEnum(String msg, String showMsg) {
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
