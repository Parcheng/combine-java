package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.handler.ElementClassHandler;
import com.parch.combine.core.ui.tools.ConfigTool;

import java.util.*;

public class PageElementManager {

    private String scopeKey;
    private final Map<String, ElementConfig<?>> CONFIGS = new HashMap<>();

    private SubConfigManager subManager;

    public PageElementManager(String scopeKey) {
        this.scopeKey = scopeKey;
        this.subManager = new SubConfigManager(scopeKey);
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

    public String load(Map<String, Object> configMap) {
        ConfigTool.initID(configMap);
        String id = (String) configMap.get(FieldKeyCanstant.ID);
        String type = (String) configMap.get(FieldKeyCanstant.TYPE);
        if (CheckEmptyUtil.isEmpty(type)) {
            PrintUtil.printError("【ui】【element】【" + id + "】【" + type + "】配置为空");
            return null;
        }

        if (CONFIGS.containsKey(id)) {
            return id;
        }

        Class<? extends ElementConfig<?>> elementClass = ElementClassHandler.get(type);
        if (elementClass == null) {
            PrintUtil.printError("【ui】【element】【" + id + "】【" + type + "】元素类型未注册");
            return null;
        }

        try {
            ElementConfig<?> config = DataParseUtil.parseJava(configMap, elementClass);
            config.init();
            subManager.build(id, config);
            CONFIGS.put(id, config);
        } catch (Exception e) {
            PrintUtil.printError("【ui】【element】【" + id + "】【" + type + "】元素配置构建失败");
            e.printStackTrace();
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
