package com.parch.combine.component.system.template;

import com.parch.combine.core.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum SystemTemplateErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    SystemTemplateErrorEnum(String msg, String showMsg) {
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
