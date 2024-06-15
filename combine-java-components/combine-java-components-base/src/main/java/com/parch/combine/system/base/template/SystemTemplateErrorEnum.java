package com.parch.combine.system.base.template;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum SystemTemplateErrorEnum implements IComponentError {

    TEMPLATE_IS_NULL("模板不存在", "模板不存在"),
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
