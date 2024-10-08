package com.parch.combine.html.common.cache;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.html.base.dataload.core.DataLoadConfig;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.cache.base.CacheModelBuilder;
import com.parch.combine.html.common.cache.base.IConfigClear;
import com.parch.combine.html.common.enums.DataLoadTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class DataloadConfigCache implements IConfigClear {

    public final static DataloadConfigCache INSTANCE = new DataloadConfigCache();

    private final static Map<String, DataloadCacheModel> CACHE = new HashMap<>();

    private DataloadConfigCache() {}

    public void register(String id, DataLoadTypeEnum type, DataLoadConfig config, CombineManager manager) {
        CacheModelBuilder builder = new CacheModelBuilder(id, type.name(), config, manager);
        DataloadCacheModel model = builder.build(new DataloadCacheModel());
        model.type = type;
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

    public static class DataloadCacheModel extends BaseCacheModel {
        public DataLoadTypeEnum type;
    }
}
