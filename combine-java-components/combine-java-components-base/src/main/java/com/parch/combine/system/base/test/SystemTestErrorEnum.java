package com.parch.combine.system.base.test;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 异常枚举
 */
public enum SystemTestErrorEnum implements IComponentError {

    LEVEL_ERROR("日志级别不合规", "日志级别不合规"),
    ;

    private String msg;

    private String showMsg;

    SystemTestErrorEnum(String msg, String showMsg) {
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
