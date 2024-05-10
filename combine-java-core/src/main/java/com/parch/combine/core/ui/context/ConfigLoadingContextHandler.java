package com.parch.combine.core.ui.context;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.manager.CombineManager;

public class ConfigLoadingContextHandler {

    private static final ThreadLocal<ConfigLoadingContext> CACHE = new ThreadLocal<>();

    public static void init(CombineManager manager, String baseUrl, String systemUrl) {
        ConfigLoadingContext context = new ConfigLoadingContext();
        context.setScopeKey(manager.getScopeKey());
        context.setBaseUrl(baseUrl == null ? CheckEmptyUtil.EMPTY : baseUrl);
        context.setSystemUrl(systemUrl == null ? CheckEmptyUtil.EMPTY : systemUrl);
        context.setManager(manager);
        CACHE.set(context);
    }

    /**
     * 获取全局上下文对象
     *
     * @return 上下文对象
     */
    public static ConfigLoadingContext getContext() {
        return CACHE.get();
    }

    /**
     * 清除上下文
     */
    public static void clear() {
        CACHE.remove();
    }
}
