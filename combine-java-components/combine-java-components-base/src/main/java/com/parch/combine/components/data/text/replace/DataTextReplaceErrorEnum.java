package com.parch.combine.components.data.text.replace;

import com.parch.combine.core.component.error.IComponentError;

public enum DataTextReplaceErrorEnum implements IComponentError {

    FAIL("失败", "失败"),
    ;

    private String msg;

    private String showMsg;

    DataTextReplaceErrorEnum(String msg, String showMsg) {
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
