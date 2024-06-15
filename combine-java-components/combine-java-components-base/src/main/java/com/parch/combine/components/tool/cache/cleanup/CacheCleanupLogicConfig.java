package com.parch.combine.components.tool.cache.cleanup;

import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.components.tool.cache.CacheLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public interface CacheCleanupLogicConfig extends CacheLogicConfig {

    @Field(key = "keyMatchRule", name = "KEY匹配规则", type = FieldTypeEnum.SELECT, defaultValue = "EXACT")
    @FieldSelect(enumClass = CacheKeyMatchRuleEnum.class)
    String keyMatchRule();

    @Field(key = "mode", name = "清理模式", type = FieldTypeEnum.SELECT, defaultValue = "ALL")
    @FieldSelect(enumClass = CacheCleanupModeEnum.class)
    String mode();

    @Field(key = "maxCount", name = "最大清理数量", type = FieldTypeEnum.NUMBER, defaultValue = "-1（无限制）")
    @FieldDesc("如果实际要清理的KEY数量大于该值，则实际清理数量以该值为准")
    Integer maxCount();
}
