package com.parch.combine.gui.base.operations.value.set;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIOperationValueSetErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIOperationValueSetErrorEnum(String msg, String showMsg) {
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
