package com.parch.combine.components.ui.doc.elements;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum UIElementDocSettingsErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    UIElementDocSettingsErrorEnum(String msg, String showMsg) {
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
