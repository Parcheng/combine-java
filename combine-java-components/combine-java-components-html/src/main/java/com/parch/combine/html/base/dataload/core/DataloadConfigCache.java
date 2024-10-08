package com.parch.combine.html.base.dataload.core;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.html.base.IConfigClear;
import com.parch.combine.html.common.tool.ConfigParseTool;

import java.util.HashMap;
import java.util.Map;

public class DataloadConfigCache implements IConfigClear {

    public final static DataloadConfigCache INSTANCE = new DataloadConfigCache();

    private final static Map<String, DataloadCacheModel> CACHE = new HashMap<>();

    private DataloadConfigCache() {}

    public void register(String id, DataLoadTypeEnum type, DataLoadConfig config) {
        DataloadCacheModel model = new DataloadCacheModel();
        model.id = id;
        model.type = type;
        model.json = JsonUtil.serialize(ConfigParseTool.parseInterfaceToMap(id, type.name(), config));
        CACHE.put(id, model);
    }

    public DataloadCacheModel get(String key) {
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

    public static class DataloadCacheModel {
        public String id;
        public DataLoadTypeEnum type;
        public String json;
    }
}
