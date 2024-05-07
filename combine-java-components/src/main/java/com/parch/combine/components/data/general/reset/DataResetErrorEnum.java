package com.parch.combine.components.data.general.reset;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 数据替换错误信息枚举
 */
public enum DataResetErrorEnum implements IComponentError {

    NEW_VALUE_IS_NULL("新值为空", "数据异常"),
    NEW_VALUE_TYPE_ERROR("新值类型不匹配", "新值类型不匹配"),
    TYPE_ERROR("类型不合法", "类型不合法"),
    FAIL("设置新值失败", "系统错误"),
    ;

    private String msg;

    private String showMsg;

    DataResetErrorEnum(String msg, String showMsg) {
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
