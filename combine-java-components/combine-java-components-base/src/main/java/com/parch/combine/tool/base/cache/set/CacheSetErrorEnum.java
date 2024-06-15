package com.parch.combine.tool.base.cache.set;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum CacheSetErrorEnum implements IComponentError {
    DOMAIN_IS_NULL("缓存域KEY为空", "缓存域KEY为空"),
    KEY_IS_NULL("缓存KEY为空", "缓存KEY为空"),
    VALUE_IS_NULL("缓存值为空", "缓存值为空"),
    FAIL("设置缓存失败", "设置缓存失败");

    private String msg;

    private String showMsg;

    CacheSetErrorEnum(String msg, String showMsg) {
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
