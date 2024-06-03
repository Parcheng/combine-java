package com.parch.combine.components.tool.cache;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface CacheLogicConfig extends ILogicConfig {

    @Field(key = "domain", name = "缓存域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    default String domain() {
        return "$common";
    }

    @Field(key = "key", name = "缓存KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();
}
