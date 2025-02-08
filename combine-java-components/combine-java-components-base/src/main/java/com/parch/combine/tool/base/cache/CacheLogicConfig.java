package com.parch.combine.tool.base.cache;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface CacheLogicConfig extends ILogicConfig {

    @Field(key = "domain", name = "缓存域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    String domain();

    @Field(key = "key", name = "缓存KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();
}
