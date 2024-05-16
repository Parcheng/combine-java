package com.parch.combine.components.data.general.verify;

import com.parch.combine.core.component.error.IComponentError;

public enum DataVerifyErrorEnum implements IComponentError {

    VALUE_TYPE_NOT_NUMBER("值非数字", "数据异常"),
    CONDITION_TYPE_ERROR("条件类型错误", "数据异常"),
    MODE_ERROR("验证模式不合规", "数据异常"),
    VERIFY_NO_PASS("验证结果 %s", "%s"),
    ;

    private String msg;

    private String showMsg;

    DataVerifyErrorEnum(String msg, String showMsg) {
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
