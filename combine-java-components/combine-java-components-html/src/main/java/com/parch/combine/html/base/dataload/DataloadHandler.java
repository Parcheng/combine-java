package com.parch.combine.html.base.dataload;

import java.util.HashMap;
import java.util.Map;

public class DataloadHandler {

    private final static Map<String, String> CACHE = new HashMap<>();

    public static void register(String key, String json) {
        CACHE.put(key, json);
    }

    public static String get(String key) {
        return CACHE.get(key);
    }

    public static void clear(String key) {
        CACHE.remove(key);
    }
}
