package com.parch.combine.gui.base.control.checkbox;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlCheckboxErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIControlCheckboxErrorEnum(String msg, String showMsg) {
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
