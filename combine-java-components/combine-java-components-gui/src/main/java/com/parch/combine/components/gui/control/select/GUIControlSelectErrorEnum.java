package com.parch.combine.components.gui.control.select;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlSelectErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIControlSelectErrorEnum(String msg, String showMsg) {
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
