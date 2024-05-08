package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.ui.base.PageConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageManager {

    private final Map<String, PageConfig> CONFIGS = new HashMap<>();

    protected List<String> load(Map<String, Map<String, Object>> configs) {
        List<String> ids = new ArrayList<>();
        if (configs == null) {
            return ids;
        }

        configs.forEach((key, value) -> ids.add(load(key, value)));
        return ids;
    }

    protected String load(String key, Map<String, Object> configMap) {
        if (CONFIGS.containsKey(key)) {
            return key;
        }

        CONFIGS.put(key, TypeConversionUtil.parseJava(configMap, PageConfig.class));
        return key;
    }

    public PageConfig get(String id) {
        return CONFIGS.get(id);
    }
}
