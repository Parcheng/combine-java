package com.parch.combine.gui.base.build.control.button;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlButtonErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIControlButtonErrorEnum(String msg, String showMsg) {
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
