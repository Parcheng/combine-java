package com.parch.combine.html.common.cache;

import com.parch.combine.html.common.cache.base.IConfigClear;

import java.util.HashMap;
import java.util.Map;

public class ElementGroupConfigCache implements IConfigClear {

    public final static ElementGroupConfigCache INSTANCE = new ElementGroupConfigCache();

    private final static Map<String, GroupCacheModel> CACHE = new HashMap<>();

    private ElementGroupConfigCache() {}

    public void register(String id, String[] elementIds) {
        GroupCacheModel model = new GroupCacheModel();
        model.id = id;
        model.elementIds = elementIds;
        CACHE.put(id, model);
    }

    public GroupCacheModel get(String key) {
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

    public static class GroupCacheModel {
        public String id;
        public String[] elementIds;
    }
}
