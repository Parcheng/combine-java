package com.parch.combine.html.base.trigger.core;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.html.base.IConfigClear;
import com.parch.combine.html.common.tool.ConfigParseTool;

import java.util.HashMap;
import java.util.Map;

public class TriggerConfigCache implements IConfigClear {

    public final static TriggerConfigCache INSTANCE = new TriggerConfigCache();

    private final static Map<String, TriggerCacheModel> CACHE = new HashMap<>();

    private TriggerConfigCache() {}

    public void register(String id, TriggerTypeEnum type, TriggerConfig config) {
        TriggerCacheModel model = new TriggerCacheModel();
        model.id = id;
        model.type = type;
        model.json = JsonUtil.serialize(ConfigParseTool.parseInterfaceToMap(id, type.name(), config));
        CACHE.put(id, model);
    }

    public TriggerCacheModel get(String key) {
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

    public static class TriggerCacheModel {
        public String id;
        public TriggerTypeEnum type;
        public String json;
    }
}
