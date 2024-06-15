package com.parch.combine.data.base.general.edit;

import com.parch.combine.core.component.error.IComponentError;

public enum DataEditErrorEnum implements IComponentError {
    PARAM_DATA_ERROR("参数类型错误", "数据异常"),
    SET_KEY_IS_NULL("要设置的 KEY 为空", "数据异常"),
    TYPE_ERROR("数据类型和处理方式不匹配", "数据异常"),
    UNKNOWN_TYPE("未知的处理类型", "数据异常"),
    FAIL("创建失败", "创建失败"),
    ;

    private String msg;

    private String showMsg;

    DataEditErrorEnum(String msg, String showMsg) {
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
