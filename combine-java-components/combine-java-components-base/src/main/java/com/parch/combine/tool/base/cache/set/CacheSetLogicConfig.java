package com.parch.combine.tool.base.cache.set;

import com.parch.combine.tool.base.cache.CacheLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface CacheSetLogicConfig extends CacheLogicConfig {

    @Field(key = "value", name = "数据/数据引用", type = FieldTypeEnum.TEXT, isRequired = true)
    String value();

    @Field(key = "expires", name = "有效期（默认无限制）", type = FieldTypeEnum.NUMBER, defaultValue = "-1")
    Long expires();
}
