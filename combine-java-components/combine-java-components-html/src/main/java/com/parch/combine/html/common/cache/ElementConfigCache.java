package com.parch.combine.html.common.cache;

import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.html.base.element.core.ElementConfig;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.cache.base.CacheModelBuilder;
import com.parch.combine.html.common.cache.base.IConfigClear;
import com.parch.combine.html.common.cache.base.IConfigGet;

import java.util.HashMap;
import java.util.Map;

public class ElementConfigCache implements IConfigClear, IConfigGet<ElementConfigCache.ElementCacheModel> {

    public final static ElementConfigCache INSTANCE = new ElementConfigCache();

    private final static Map<String, ElementCacheModel> CACHE = new HashMap<>();

    private ElementConfigCache() {}

    public void register(String id, String type, ElementConfig config, CombineManager manager) {
        CacheModelBuilder builder = new CacheModelBuilder(id, type, config, manager);
        ElementCacheModel model = builder.build(new ElementCacheModel());
        model.type = type;
        model.loadId = config.dataLoad();
        model.templateId = config.template();
        CACHE.put(id, model);
    }

    @Override
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
        public String loadId;
        public String templateId;
    }
}
