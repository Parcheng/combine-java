package com.parch.combine.components.data.general.format;

import com.parch.combine.core.error.IComponentError;

public enum DataFormatErrorEnum implements IComponentError {

    FAIL("格式化失败", "格式化失败"),
    ;

    private String msg;

    private String showMsg;

    DataFormatErrorEnum(String msg, String showMsg) {
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
