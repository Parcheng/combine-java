package com.parch.combine.tool.base.cache;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum CacheErrorEnum implements IComponentError {

    DOMAIN_IS_NULL("缓存域KEY为空", "缓存域KEY为空"),
    KEY_IS_NULL("缓存KEY为空", "缓存KEY为空");

    private String msg;

    private String showMsg;

    CacheErrorEnum(String msg, String showMsg) {
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
