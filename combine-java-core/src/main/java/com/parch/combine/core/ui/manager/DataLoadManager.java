package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.ui.base.dataload.*;
import com.parch.combine.core.ui.tools.ConfigTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoadManager  {

    private final Map<String, DataLoadConfig> CONFIGS = new HashMap<>();

    protected List<String> load(List<Map<String, Object>> configs) {
        List<String> ids = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (Map<String, Object> item : configs) {
                ids.add(load(item));
            }
        }

        return ids;
    }

    protected String load(Map<String, Object> configMap) {
        ConfigTool.initID(configMap);
        String id = (String) configMap.get(FieldKeyCanstant.ID);
        String type = (String) configMap.get(FieldKeyCanstant.TYPE);
        if (CheckEmptyUtil.isEmpty(type)) {
            PrintUtil.printError("【ui】【dataLoad】【" + id + "】类型为空");
            return null;
        }

        if (CONFIGS.containsKey(id)) {
            return id;
        }

        DataLoadConfig dataLoad = build(id, type, configMap);
        if (dataLoad == null) {
            return null;
        }

        dataLoad.init();
        CONFIGS.put(id, dataLoad);
        return id;
    }

    private String getKey(String id, String type) {
        return type + (id == null ? CheckEmptyUtil.EMPTY : id);
    }

    private DataLoadConfig build(String id, String type, Map<String, Object> configMap) {
        if (configMap == null) {
            PrintUtil.printError("【ui】【dataLoad】【" + id + "】【" + type + "】配置为空");
            return null;
        }

        DataLoadTypeEnum dataLoadType = DataLoadTypeEnum.get(type);
        switch (dataLoadType) {
            case FLOW:
                return TypeConversionUtil.parseJava(configMap, FlowDataLoadConfig.class);
            case API:
                return TypeConversionUtil.parseJava(configMap, ApiDataLoadConfig.class);
            case FILE:
                return TypeConversionUtil.parseJava(configMap, FileDataLoadConfig.class);
            default:
                PrintUtil.printError("【ui】【dataLoad】【" + id + "】【" + type + "】类型不存在");
                return null;
        }
    }

    public DataLoadConfig get(String id) {
        return CONFIGS.get(id);
    }
}
