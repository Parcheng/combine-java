package com.parch.combine.gui.base.module.from;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIModuleFromErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIModuleFromErrorEnum(String msg, String showMsg) {
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
