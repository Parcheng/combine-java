package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.manager.AbsPreLoadConfigManager;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.handler.ElementClassHandler;

import java.util.*;

public class PageElementManager {

    private final Map<String, ElementConfig<?>> CONFIGS = new HashMap<>();

    private SubConfigManager subManager = new SubConfigManager();

    public List<String> load(List<Map<String, Object>> configs) {
        List<String> ids = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (Map<String, Object> item : configs) {
                ids.add(load(item));
            }
        }

        return ids;
    }

    public String load(Map<String, Object> configMap) {
        String id = (String) configMap.get(FieldKeyCanstant.ID);
        String type = (String) configMap.get(FieldKeyCanstant.TYPE);
        if (CheckEmptyUtil.isEmpty(type)) {
            return null;
        }

        if (CONFIGS.containsKey(id)) {
            return id;
        }

        Class<? extends ElementConfig<?>> elementClass = ElementClassHandler.get(type);
        if (elementClass == null) {
            return null;
        }

        try {
            ElementConfig<?> config = TypeConversionUtil.parseJava(configMap, elementClass);
            config.init();
            subManager.build(id, config);
            CONFIGS.put(id, config);
        } catch (Exception e) {
            return null;
        }

        return id;
    }

    public ElementConfig<?> get(String id) {
        return CONFIGS.get(id);
    }

    public List<String> getSubElements(String key) {
        return subManager.getSubElements(key);
    }

    public List<String> getSubTriggers(String key) {
        return subManager.getSubTriggers(key);
    }
}
