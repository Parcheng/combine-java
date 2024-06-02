package com.parch.combine.components.data.enums.list;

import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class DataEnumGetLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @Override
    public void init() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
