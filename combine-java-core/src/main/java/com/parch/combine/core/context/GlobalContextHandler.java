package com.parch.combine.core.context;

import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.common.util.ResourceFileUtil;

/**
 * 全局上下文处理器
 */
public class GlobalContextHandler {

    private static GlobalContext CONTEXT;

    public static void init(String path) {
        String testConfigJson = ResourceFileUtil.read(path);
        CONTEXT = JsonUtil.deserialize(testConfigJson, GlobalContext.class);
        if (CONTEXT == null) {
            CONTEXT = new GlobalContext();
        }
    }

    public static GlobalContext get() {
        return CONTEXT;
    }
}
