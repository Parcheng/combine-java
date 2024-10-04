package com.parch.combine.ui.core.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.ui.core.tools.ConfigTool;
import com.parch.combine.ui.core.base.trigger.*;

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

        if (CONFIGS.containsKey(id)) {
            return id;
        }

        TriggerConfig trigger =  build(id, type, configMap);
        if (trigger == null) {
            return null;
        }

        trigger.init();
        CONFIGS.put(id, trigger);
        subManager.build(id, trigger);
        return id;
    }

    public TriggerConfig get(String id) {
        return CONFIGS.get(id);
    }

    public List<String> getSubElements(String key) {
        return subManager.getSubElements(key);
    }

    public List<String> getSubTriggers(String key) {
        return subManager.getSubTriggers(key);
    }

    private TriggerConfig build(String id, String type, Map<String, Object> configMap) {
        if (configMap == null) {
            PrintLogUtil.printError("【ui】【trigger】【" + id + "】【" + type + "】配置为空");
            return null;
        }

        TriggerTypeEnum triggerType = TriggerTypeEnum.get(type);
        switch (triggerType) {
            case CALL_FLOW:
                return DataParseUtil.parseJava(configMap, TriggerCallFlowConfig.class);
            case CALL_URL:
                return DataParseUtil.parseJava(configMap, TriggerCallUrlConfig.class);
            case CALL_FUNC:
                return DataParseUtil.parseJava(configMap, TriggerCallFuncConfig.class);
            case LOAD:
                return DataParseUtil.parseJava(configMap, TriggerLoadConfig.class);
            case LOAD_DATA:
                return DataParseUtil.parseJava(configMap, TriggerLoadDataConfig.class);
            case SKIP:
                return DataParseUtil.parseJava(configMap, TriggerSkipConfig.class);
            case CUSTOM:
                return DataParseUtil.parseJava(configMap, TriggerCustomConfig.class);
            default:
                PrintLogUtil.printError("【ui】【trigger】【" + id + "】【" + type + "】类型不存在");
                return null;
        }
    }

    private String getKey(String id, String type) {
        return type + (id == null ? CheckEmptyUtil.EMPTY : id);
    }
}
