package com.parch.combine.data.base.enums.mapping;

import com.parch.combine.core.component.error.IComponentError;

public enum DataEnumMappingErrorEnum implements IComponentError {

    DATA_TYPE_ERROR("不支持的数据类型", "不支持的数据类型"),
    FAIL("创建失败", "创建失败"),
    ;

    private String msg;

    private String showMsg;

    DataEnumMappingErrorEnum(String msg, String showMsg) {
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
