package com.parch.combine.ui.core.handler;

import com.parch.combine.ui.core.manager.CombineManager;

import java.util.HashMap;
import java.util.Map;


public class CombineManagerHandler {

    private final static Map<String, CombineManager> MANAGER_MAP = new HashMap<>();

    public static void register(String scopeKey, CombineManager manager) {
        MANAGER_MAP.put(scopeKey, manager);
    }

    public static CombineManager get(String scopeKey) {
        return MANAGER_MAP.get(scopeKey);
    }
}
