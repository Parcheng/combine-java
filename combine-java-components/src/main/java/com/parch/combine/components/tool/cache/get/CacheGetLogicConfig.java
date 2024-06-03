package com.parch.combine.components.tool.cache.get;

import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.components.tool.cache.CacheLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface CacheGetLogicConfig extends CacheLogicConfig {

    @Field(key = "keyMatchRule", name = "KEY匹配规则", type = FieldTypeEnum.SELECT, defaultValue = "EXACT")
    @FieldSelect(enumClass = CacheKeyMatchRuleEnum.class)
    default String keyMatchRule() {
        return "EXACT";
    }
}
