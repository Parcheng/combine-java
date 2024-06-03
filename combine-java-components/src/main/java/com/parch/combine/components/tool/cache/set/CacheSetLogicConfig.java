package com.parch.combine.components.tool.cache.set;

import com.parch.combine.components.tool.cache.CacheLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface CacheSetLogicConfig extends CacheLogicConfig {

    @Field(key = "value", name = "数据/数据引用", type = FieldTypeEnum.TEXT, isRequired = true)
    String value();

    @Field(key = "expires", name = "有效期", type = FieldTypeEnum.TEXT, defaultValue = "-1（无限制）")
    default Long expires() {
        return -1L;
    }
}
