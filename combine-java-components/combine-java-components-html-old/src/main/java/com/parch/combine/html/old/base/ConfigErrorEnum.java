package com.parch.combine.html.old.base;

import com.parch.combine.core.component.error.IComponentError;

public enum ConfigErrorEnum implements IComponentError {

    CONFIG_IS_NULL("配置为空", "配置为空"),
    ID_IS_NULL("ID为空", "ID为空"),
    ;

    private String msg;

    private String showMsg;

    ConfigErrorEnum(String msg, String showMsg) {
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
