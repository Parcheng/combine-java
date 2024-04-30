package com.parch.combine.components.tool.cache.get;

import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldSelect;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class CacheGetLogicConfig extends LogicConfig {

    @ComponentField(key = "domain", name = "缓存域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    private String domain;

    @ComponentField(key = "key", name = "缓存KEY/数据引用", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @ComponentField(key = "keyMatchRule", name = "KEY匹配规则", type = FieldTypeEnum.SELECT, defaultValue = "EXACT")
    @ComponentFieldSelect(enumClass = CacheKeyMatchRuleEnum.class)
    private String keyMatchRule;

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
