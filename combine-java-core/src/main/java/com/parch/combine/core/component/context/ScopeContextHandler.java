package com.parch.combine.core.component.context;

import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.json.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局上下文处理器
 */
public class ScopeContextHandler {

    private static Map<String, ScopeContext> MAP = new HashMap<>(1);

    public static void init(String scopeKey, String path) {
        String testConfigJson = ResourceFileUtil.read(path, ScopeContextHandler.class.getClassLoader());
        ScopeContext context = JsonUtil.string2Obj(testConfigJson, ScopeContext.class);
        if (context == null) {
            context = new ScopeContext();
        }

        MAP.put(scopeKey, context);
    }

    public static ScopeContext get(String scopeKey) {
        return MAP.get(scopeKey);
    }
}
