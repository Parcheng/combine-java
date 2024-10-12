package com.parch.combine.data.base.enums.get;

import com.parch.combine.core.component.error.IComponentError;

public enum DataEnumGetErrorEnum implements IComponentError {

    KEY_IS_NULL("枚举KEY为空", "枚举不存在"),
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
