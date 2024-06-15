package com.parch.combine.tool.base.cache.get;

import com.parch.combine.tool.base.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.tool.base.cache.CacheLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface CacheGetLogicConfig extends CacheLogicConfig {

    @Field(key = "keyMatchRule", name = "KEY匹配规则", type = FieldTypeEnum.SELECT, defaultValue = "EXACT")
    @FieldSelect(enumClass = CacheKeyMatchRuleEnum.class)
    String keyMatchRule();
}
