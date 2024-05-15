package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.ui.base.trigger.*;
import com.parch.combine.core.ui.tools.ConfigTool;

import java.util.*;

public class TriggerManager {

    private final Map<String, TriggerConfig> CONFIGS = new HashMap<>();

    private String scopeKey;
    private SubConfigManager subManager;

    public TriggerManager(String scopeKey) {
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
            return null;
        }

        String key = getKey(id, type);
        if (CONFIGS.containsKey(key)) {
            return id;
        }

        TriggerConfig trigger =  build(id, type, configMap);
        if (trigger == null) {
            return null;
        }

        trigger.init();
        CONFIGS.put(key, trigger);
        subManager.build(key, trigger);
        return id;
    }

    public TriggerConfig get(String key) {
        return CONFIGS.get(key);
    }

    public List<String> getSubElements(String key) {
        return subManager.getSubElements(key);
    }

    public List<String> getSubTriggers(String key) {
        return subManager.getSubTriggers(key);
    }

    private TriggerConfig build(String id, String type, Map<String, Object> configMap) {
        if (configMap == null) {
            PrintUtil.printError("【ui】【trigger】【" + id + "】【" + type + "】配置为空");
            return null;
        }

        TriggerTypeEnum triggerType = TriggerTypeEnum.get(type);
        switch (triggerType) {
            case CALL_FLOW:
                return TypeConversionUtil.parseJava(configMap, TriggerCallFlowConfig.class);
            case CALL_URL:
                return TypeConversionUtil.parseJava(configMap, TriggerCallUrlConfig.class);
            case CALL_FUNC:
                return TypeConversionUtil.parseJava(configMap, TriggerCallFuncConfig.class);
            case LOAD:
                return TypeConversionUtil.parseJava(configMap, TriggerLoadEntity.class);
            case LOAD_DATA:
                return TypeConversionUtil.parseJava(configMap, TriggerLoadDataEntity.class);
            case SKIP:
                return TypeConversionUtil.parseJava(configMap, TriggerSkipEntity.class);
            case CUSTOM:
                return TypeConversionUtil.parseJava(configMap, TriggerCustomEntity.class);
            default:
                PrintUtil.printError("【ui】【trigger】【" + id + "】【" + type + "】类型不存在");
                return null;
        }
    }

    private String getKey(String id, String type) {
        return type + (id == null ? CheckEmptyUtil.EMPTY : id);
    }
}
