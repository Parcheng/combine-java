package com.parch.combine.core.ui.handler;

import com.parch.combine.core.ui.manager.CombineManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心处理器
 */
public class CombineManagerHandler {

    private final static Map<String, CombineManager> MANAGER_MAP = new HashMap<>();

    public static void register(String scopeKey, CombineManager manager) {
        MANAGER_MAP.put(scopeKey, manager);
    }

    public static CombineManager get(String scopeKey) {
        return MANAGER_MAP.get(scopeKey);
    }
}
