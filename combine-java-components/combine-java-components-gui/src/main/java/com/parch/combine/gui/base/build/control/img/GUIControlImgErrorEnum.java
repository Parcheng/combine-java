package com.parch.combine.gui.base.build.control.img;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIControlImgErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    GUIControlImgErrorEnum(String msg, String showMsg) {
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
