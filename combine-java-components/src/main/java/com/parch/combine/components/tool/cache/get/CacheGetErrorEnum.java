package com.parch.combine.components.tool.cache.get;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum CacheGetErrorEnum implements IComponentError {

    KEY_MATCH_RULE_IS_ERROR("未知的匹配规则", "未知的匹配规则"),
    FAIL("获取缓存失败", "获取缓存失败");

    private final String msg;

    private final String showMsg;

    CacheGetErrorEnum(String msg, String showMsg) {
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
