package com.parch.combine.components.data.enums.register;

import com.parch.combine.core.component.error.IComponentError;

public enum DataEnumRegisterErrorEnum implements IComponentError {

    FAIL("创建失败", "创建失败"),
    ;

    private String msg;

    private String showMsg;

    DataEnumRegisterErrorEnum(String msg, String showMsg) {
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
