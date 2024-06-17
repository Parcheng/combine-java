package com.parch.combine.gui.base.control.slider;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlSliderErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIControlSliderErrorEnum(String msg, String showMsg) {
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
