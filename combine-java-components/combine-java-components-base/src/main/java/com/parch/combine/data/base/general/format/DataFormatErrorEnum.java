package com.parch.combine.data.base.general.format;

import com.parch.combine.core.component.error.IComponentError;

public enum DataFormatErrorEnum implements IComponentError {

    PARAMS_ERROR("函数参数不合规", "函数配置错误"),
    FUNCTION_IS_NULL("函数不存在", "函数配置错误"),
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
