package com.parch.combine.core.component.context;

import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.json.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局上下文处理器
 */
public class GlobalContextHandler {

    private static Map<String, GlobalContext> MAP = new HashMap<>(1);

    public static void init(String scopeKey, String path) {
        String testConfigJson = ResourceFileUtil.read(path, GlobalContextHandler.class.getClassLoader());
        GlobalContext context = JsonUtil.string2Obj(testConfigJson, GlobalContext.class);
        if (context == null) {
            context = new GlobalContext();
        }

        MAP.put(scopeKey, context);
    }

    public static GlobalContext get(String scopeKey) {
        return MAP.get(scopeKey);
    }
}
