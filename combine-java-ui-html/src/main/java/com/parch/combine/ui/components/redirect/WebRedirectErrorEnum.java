package com.parch.combine.ui.components.redirect;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 数据替换错误信息枚举
 */
public enum WebRedirectErrorEnum implements IComponentError {

    PATH_IS_NULL("重定向地址为空", "重定向地址为空"),
    ;

    private final String msg;

    private final String showMsg;

    WebRedirectErrorEnum(String msg, String showMsg) {
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
