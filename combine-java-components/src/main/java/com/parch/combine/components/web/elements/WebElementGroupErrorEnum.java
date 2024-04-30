package com.parch.combine.components.web.elements;

import com.parch.combine.core.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum WebElementGroupErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    WebElementGroupErrorEnum(String msg, String showMsg) {
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
