package com.parch.combine.data.base.general.mapping;

import com.parch.combine.core.component.error.IComponentError;

public enum DataMappingErrorEnum implements IComponentError {

    FAIL("替换失败", "数据异常"),
    ;

    private String msg;

    private String showMsg;

    DataMappingErrorEnum(String msg, String showMsg) {
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
