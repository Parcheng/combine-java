package com.parch.combine.ui.core.tools;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.Map;
import java.util.UUID;

public class ConfigTool {

    public static void initID(Map<String, Object> configMap) {
        String id = (String) configMap.get(FieldKeyCanstant.ID);
        if (CheckEmptyUtil.isEmpty(id)) {
            id = UUID.randomUUID().toString();
            configMap.put(FieldKeyCanstant.ID, id);
        }
    }
}
