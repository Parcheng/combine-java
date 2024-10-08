package com.parch.combine.html.common.cache;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.cache.base.CacheModelBuilder;
import com.parch.combine.html.common.cache.base.IConfigClear;

import java.util.HashMap;
import java.util.Map;

public class ElementTemplateConfigCache implements IConfigClear {

    public final static ElementTemplateConfigCache INSTANCE = new ElementTemplateConfigCache();

    private final static Map<String, TemplateCacheModel> CACHE = new HashMap<>();

    private ElementTemplateConfigCache() {}

    public void register(String id, String type, ElementTemplateConfig config, CombineManager manager) {
        CacheModelBuilder builder = new CacheModelBuilder(id, type, config, manager);
        TemplateCacheModel model = builder.build(new TemplateCacheModel());
        model.type = type;
        CACHE.put(id, model);
    }

    public TemplateCacheModel get(String key) {
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

    public static class TemplateCacheModel extends BaseCacheModel {
        public String type;
    }
}
