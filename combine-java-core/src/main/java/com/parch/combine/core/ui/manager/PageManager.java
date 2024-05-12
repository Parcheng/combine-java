package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.*;
import com.parch.combine.core.ui.base.HtmlConfig;

import java.util.*;

public class PageManager {

    private final Map<String, HtmlConfig> CONFIGS = new HashMap<>();

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

        CONFIGS.put(key, TypeConversionUtil.parseJava(configMap, HtmlConfig.class));
        return key;
    }

    public HtmlConfig get(String id) {
        return CONFIGS.get(id);
    }

    public Map<String, HtmlConfig> get() {
        return CONFIGS;
    }
}
