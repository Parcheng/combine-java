package com.parch.combine.gui.base.operations;

import com.parch.combine.core.component.error.IComponentError;

public enum GUIOperationErrorEnum implements IComponentError {

    ELEMENT_DOMAIN_NOT_EXIST("GUI元素域不存在", "GUI元素域不存在"),
    ELEMENT_NOT_EXIST("GUI元素不存在", "GUI元素不存在"),
    ;

    private String msg;

    private String showMsg;

    GUIOperationErrorEnum(String msg, String showMsg) {
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
