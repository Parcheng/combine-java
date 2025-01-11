package com.parch.combine.data.base.text.join;

import com.parch.combine.core.component.error.IComponentError;

public enum DataTextJoinErrorEnum implements IComponentError {

    FAIL("失败", "失败"),
    ;

    private String msg;

    private String showMsg;

    DataTextJoinErrorEnum(String msg, String showMsg) {
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
