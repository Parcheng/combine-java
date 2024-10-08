package com.parch.combine.html.common.cache;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.html.base.element.core.ElementConfig;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.cache.base.CacheModelBuilder;
import com.parch.combine.html.common.cache.base.IConfigClear;

import java.util.HashMap;
import java.util.Map;

public class ElementConfigCache implements IConfigClear {

    public final static ElementConfigCache INSTANCE = new ElementConfigCache();

    private final static Map<String, ElementCacheModel> CACHE = new HashMap<>();

    private ElementConfigCache() {}

    public void register(String id, String type, ElementConfig config) {
        ElementCacheModel model = new ElementCacheModel();
        model.id = id;
        model.type = type;
        model.json = JsonUtil.serialize(CacheModelBuilder.parseInterfaceToMap(id, type, config));
        CACHE.put(id, model);
    }

    public ElementCacheModel get(String key) {
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

    public static class ElementCacheModel extends BaseCacheModel {
        public String type;
    }
}
