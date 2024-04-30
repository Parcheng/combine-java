package com.parch.combine.components.access.redis.command;

import com.parch.combine.core.error.IComponentError;

public enum RedisCommandErrorEnum implements IComponentError {
    UNKNOWN_ERROR("执行失败", "执行失败"),
    ;

    private String msg;

    private String showMsg;

    RedisCommandErrorEnum(String msg, String showMsg) {
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
