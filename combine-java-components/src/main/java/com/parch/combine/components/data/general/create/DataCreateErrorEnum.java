package com.parch.combine.components.data.general.create;

import com.parch.combine.core.component.error.IComponentError;

public enum DataCreateErrorEnum implements IComponentError {

    NUMBER_ERROR("数字类型的默认值必须为数字", "创建数字失败"),
    DATE_ERROR("创建日期失败", "创建日期失败"),
    FAIL("创建失败", "创建失败"),
    ;

    private String msg;

    private String showMsg;

    DataCreateErrorEnum(String msg, String showMsg) {
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
