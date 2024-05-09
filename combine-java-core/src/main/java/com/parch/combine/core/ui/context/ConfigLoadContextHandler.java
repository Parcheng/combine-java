package com.parch.combine.core.ui.context;

import com.parch.combine.core.common.util.CheckEmptyUtil;

public class ConfigLoadContextHandler {

    private static final ThreadLocal<ConfigLoadContext> CACHE = new ThreadLocal<>();

    public static void init(String scopeKey, String baseUrl, String systemUrl) {
        clear();
        ConfigLoadContext context = new ConfigLoadContext();
        context.setScopeKey(scopeKey);
        context.setBaseUrl(baseUrl == null ? CheckEmptyUtil.EMPTY : baseUrl);
        context.setSystemUrl(systemUrl == null ? CheckEmptyUtil.EMPTY : systemUrl);
        CACHE.set(context);
    }

    /**
     * 获取全局上下文对象
     *
     * @return 上下文对象
     */
    public static ConfigLoadContext getContext() {
        return CACHE.get();
    }

    /**
     * 清除上下文
     */
    public static void clear() {
        CACHE.remove();
    }
}
