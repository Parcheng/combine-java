package com.parch.combine.html.base.dataload;

import com.parch.combine.html.base.IConfigClear;

import java.util.HashMap;
import java.util.Map;

public class DataloadConfigCache implements IConfigClear {

    public final static DataloadConfigCache INSTANCE = new DataloadConfigCache();

    private final static Map<String, String> CACHE = new HashMap<>();

    private DataloadConfigCache() {}

    public void register(String key, String json) {
        CACHE.put(key, json);
    }

    public String get(String key) {
        return CACHE.get(key);
    }

    @Override
    public void clear(String key) {
        CACHE.remove(key);
    }

    @Override
    public void clear() {
        CACHE.clear();
    }
}
