package com.parch.combine.core.ui.handler;

import com.parch.combine.core.ui.base.element.ElementConfig;

import java.util.HashMap;
import java.util.Map;

public class ElementClassHandler {

    private final static Map<String, ElementConfig<?,?>> ELEMENT_CLASS_MAP = new HashMap<>(16);

    public synchronized static void register(String key, ElementConfig<?,?> elementConfigClass) {
        ELEMENT_CLASS_MAP.put(key, elementConfigClass);
    }

    public static ElementConfig<?,?> get(String key) {
        return ELEMENT_CLASS_MAP.get(key);
    }
}
