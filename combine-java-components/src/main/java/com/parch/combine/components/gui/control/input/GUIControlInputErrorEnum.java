package com.parch.combine.components.gui.control.input;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlInputErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIControlInputErrorEnum(String msg, String showMsg) {
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
