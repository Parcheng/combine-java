package com.parch.combine.components.data.enums.list;

import com.parch.combine.core.error.IComponentError;

public enum DataEnumGetErrorEnum implements IComponentError {

    ENUM_NO_REGISTER("枚举未注册", "枚举未注册"),
    FAIL("创建失败", "创建失败"),
    ;

    private String msg;

    private String showMsg;

    DataEnumGetErrorEnum(String msg, String showMsg) {
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
