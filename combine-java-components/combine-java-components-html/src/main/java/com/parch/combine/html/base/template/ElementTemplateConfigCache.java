package com.parch.combine.html.base.template;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.html.base.IConfigClear;
import com.parch.combine.html.core.tool.ConfigParseTool;

import java.util.HashMap;
import java.util.Map;

public class ElementTemplateConfigCache implements IConfigClear {

    public final static ElementTemplateConfigCache INSTANCE = new ElementTemplateConfigCache();

    private final static Map<String, TemplateCacheModel> CACHE = new HashMap<>();

    private ElementTemplateConfigCache() {}

    public void register(String id, String type, ElementTemplateConfig config) {
        TemplateCacheModel model = new TemplateCacheModel();
        model.id = id;
        model.type = type;
        model.json = JsonUtil.serialize(ConfigParseTool.parseInterfaceToMap(id, type, config));
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

    public static class TemplateCacheModel {
        public String id;
        public String type;
        public String json;
    }
}
