package com.parch.combine.components.tool.cache.get;

import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.components.tool.cache.CacheLogicConfig;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class CacheGetLogicConfig extends CacheLogicConfig {

    @Field(key = "keyMatchRule", name = "KEY匹配规则", type = FieldTypeEnum.SELECT, defaultValue = "EXACT")
    @FieldSelect(enumClass = CacheKeyMatchRuleEnum.class)
    private String keyMatchRule;

    @Override
    public void init() {
        super.init();
        if (CheckEmptyUtil.isEmpty(this.keyMatchRule)) {
            this.keyMatchRule = "EXACT";
        }
    }

    public String getKeyMatchRule() {
        return keyMatchRule;
    }

    public void setKeyMatchRule(String keyMatchRule) {
        this.keyMatchRule = keyMatchRule;
    }
}
