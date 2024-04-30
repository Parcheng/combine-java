package com.parch.combine.components.tool.cache.cleanup;

import com.parch.combine.core.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum CacheCleanupErrorEnum implements IComponentError {

    KEY_IS_NULL("缓存KEY为空", "缓存KEY为空"),
    MODE_ERROR("未知类型", "未知类型"),
    KEY_MATCH_RULE_IS_ERROR("未知的匹配规则", "未知的匹配规则"),
    FAIL("清理缓存失败", "清理缓存失败");

    private String msg;

    private String showMsg;

    CacheCleanupErrorEnum(String msg, String showMsg) {
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
