package com.parch.combine.components.gui.control.radio;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlRadioErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIControlRadioErrorEnum(String msg, String showMsg) {
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
