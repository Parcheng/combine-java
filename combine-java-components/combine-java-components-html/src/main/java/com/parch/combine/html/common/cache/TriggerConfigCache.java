package com.parch.combine.html.common.cache;

import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.html.base.trigger.core.TriggerConfig;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.cache.base.CacheModelBuilder;
import com.parch.combine.html.common.cache.base.IConfigClear;
import com.parch.combine.html.common.cache.base.IConfigGet;
import com.parch.combine.html.common.enums.TriggerTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class TriggerConfigCache implements IConfigClear, IConfigGet<TriggerConfigCache.TriggerCacheModel> {

    public final static TriggerConfigCache INSTANCE = new TriggerConfigCache();

    private final static Map<String, TriggerCacheModel> CACHE = new HashMap<>();

    private TriggerConfigCache() {}

    public void register(String id, TriggerTypeEnum type, TriggerConfig config, CombineManager manager) {
        CacheModelBuilder builder = new CacheModelBuilder(id, type.name(), config, manager);
        TriggerCacheModel model = builder.build(new TriggerCacheModel());
        model.type = type;
        CACHE.put(id, model);
    }

    @Override
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

    public static class TriggerCacheModel extends BaseCacheModel {
        public TriggerTypeEnum type;
    }
}
