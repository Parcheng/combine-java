package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.tuple.ThreeTuples;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.tools.config.ConfigHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程初始化配置处理器
 */
public class InitConfigManager {

    private String scopeKey;
    protected final Map<String, IInitConfig> CONFIGS = new HashMap<>();
    protected final Map<String, Map<String, Object>> PRE_CONFIGS = new HashMap<>();

    public InitConfigManager(String scopeKey) {
        this.scopeKey = scopeKey;
    }

    public List<String> load(List<Map<String, Object>> configs) {
        List<String> ids = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (Map<String, Object> item : configs) {
                ids.add(load(item));
            }
        }

        return ids;
    }

    public String load(Map<String, Object> item) {
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

    @SuppressWarnings("unchecked")
    public <T extends IInitConfig> ThreeTuples<Boolean, T, List<String>> get(String id, String type, Class<T> clazz) {
        String key = getKey(id, type);
        T config = (T) CONFIGS.get(key);
        if (config != null) {
            return new ThreeTuples<>(true, config, null);
        }

        Map<String, Object> preConfig = PRE_CONFIGS.get(key);
        if (preConfig == null) {
            preConfig = new HashMap<>();
            preConfig.put(FieldKeyCanstant.ID, id);
            preConfig.put(FieldKeyCanstant.TYPE, type);
        }

        ThreeTuples<Boolean, T, List<String>> buildResult = ConfigHelper.build(scopeKey, clazz, preConfig);
        if (buildResult.getFirst()) {
            CONFIGS.put(key, buildResult.getSecond());
            PRE_CONFIGS.remove(key);
            return new ThreeTuples<>(true, buildResult.getSecond(), null);
        } else {
            return new ThreeTuples<>(false, null, buildResult.getThird());
        }
    }

    public void clear() {
        CONFIGS.clear();
        PRE_CONFIGS.clear();
    }

    private boolean check(String id, String type) {
        String key = getKey(id, type);
        return CONFIGS.containsKey(key) || PRE_CONFIGS.containsKey(key);
    }

    protected String getKey(String id, String type) {
        return (type + (id == null ? CheckEmptyUtil.EMPTY : id)).toUpperCase();
    }
}
