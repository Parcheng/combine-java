package com.parch.combine.components.tool.cache.get;

import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class CacheGetLogicConfig extends LogicConfig {

    @Field(key = "domain", name = "缓存域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    private String domain;

    @Field(key = "key", name = "缓存KEY/数据引用", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @Field(key = "keyMatchRule", name = "KEY匹配规则", type = FieldTypeEnum.SELECT, defaultValue = "EXACT")
    @FieldSelect(enumClass = CacheKeyMatchRuleEnum.class)
    private String keyMatchRule;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(this.domain)) {
            this.domain = "$common";
        }
        if (CheckEmptyUtil.isEmpty(this.keyMatchRule)) {
            this.keyMatchRule = "EXACT";
        }
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyMatchRule() {
        return keyMatchRule;
    }

    public void setKeyMatchRule(String keyMatchRule) {
        this.keyMatchRule = keyMatchRule;
    }
}
