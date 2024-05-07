package com.parch.combine.components.system.doc.config;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum SystemDocConfigErrorEnum implements IComponentError {

    FAIL("系统设置不存在", "获取系统设置失败"),
    ;

    private String msg;

    private String showMsg;

    SystemDocConfigErrorEnum(String msg, String showMsg) {
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
