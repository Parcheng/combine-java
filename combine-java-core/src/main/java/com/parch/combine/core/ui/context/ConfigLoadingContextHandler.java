package com.parch.combine.core.ui.context;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.ui.vo.GlobalConfigVO;

public class ConfigLoadingContextHandler {

    private static final ThreadLocal<ConfigLoadingContext> CACHE = new ThreadLocal<>();

    public static ConfigLoadingContext build(GlobalConfigVO globalConfig) {
        ConfigLoadingContext context = new ConfigLoadingContext();
        context.setBaseUrl(globalConfig.getBaseUrl() == null ? CheckEmptyUtil.EMPTY : globalConfig.getBaseUrl());
        context.setSystemUrl(globalConfig.getSystemUrl() == null ? CheckEmptyUtil.EMPTY : globalConfig.getSystemUrl());
        context.setFlagConfigsJson(JsonUtil.serialize(globalConfig.getFlagConfigs()));
        return context;
    }

    public static void set(ConfigLoadingContext context) {
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
