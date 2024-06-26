package com.parch.combine.redis.base.lua;

import com.parch.combine.core.component.error.IComponentError;

public enum RedisLuaErrorEnum implements IComponentError {
    FAIL("锁操作失败", "锁操作失败"),
    ;

    private String msg;

    private String showMsg;

    RedisLuaErrorEnum(String msg, String showMsg) {
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
