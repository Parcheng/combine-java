package com.parch.combine.components.data.enums.list;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class DataEnumGetLogicConfig extends LogicConfig {

    @ComponentField(key = "key", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
