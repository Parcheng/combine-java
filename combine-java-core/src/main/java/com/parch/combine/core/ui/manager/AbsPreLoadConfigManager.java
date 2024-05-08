package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsPreLoadConfigManager<C> {

    private final Map<String, C> CONFIGS = new HashMap<>();

    private final Map<String, Map<String, Object>> PRE_CONFIGS = new HashMap<>();

    protected List<String> load(List<Map<String, Object>> configs) {
        List<String> ids = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (Map<String, Object> item : configs) {
                ids.add(load(item));
            }
        }

        return ids;
    }

    protected String load(Map<String, Object> item) {
        String id = (String) item.get(FieldKeyCanstant.ID);
        String type = (String) item.get(FieldKeyCanstant.TYPE);
        if (CheckEmptyUtil.isEmpty(type)) {
            return null;
        }

        if (check(id, type)) {
            return id;
        }

        PRE_CONFIGS.put(getKey(id, type), item);
        return id;
    }

    public C get(String id, String type) {
        String key = getKey(id, type);
        C config = CONFIGS.get(key);
        if (config != null) {
            return config;
        }

        Map<String, Object> preConfig = PRE_CONFIGS.get(key);
        C newConfig = CONFIGS.put(key, this.initConfig(id, type, preConfig));;
        if (newConfig == null) {
            return null;
        }

        CONFIGS.put(key, newConfig);
        PRE_CONFIGS.remove(key);

        return newConfig;
    }

    public void clear() {
        CONFIGS.clear();
        PRE_CONFIGS.clear();
    }

    private boolean check(String id, String type) {
        String key = getKey(id, type);
        return CONFIGS.containsKey(key) || PRE_CONFIGS.containsKey(key);
    }

    private String getKey(String id, String type) {
        return type + (id == null ? CheckEmptyUtil.EMPTY : id);
    }

    protected abstract C initConfig(String id, String type, Map<String, Object> configMap);
}
