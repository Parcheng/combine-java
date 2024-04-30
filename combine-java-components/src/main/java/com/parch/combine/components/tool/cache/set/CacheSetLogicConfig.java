package com.parch.combine.components.tool.cache.set;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class CacheSetLogicConfig extends LogicConfig {

    @ComponentField(key = "domain", name = "缓存域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    private String domain;

    @ComponentField(key = "key", name = "缓存KEY/数据引用", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @ComponentField(key = "value", name = "数据/数据引用", type = FieldTypeEnum.TEXT, isRequired = true)
    private String value;

    @ComponentField(key = "expires", name = "有效期", type = FieldTypeEnum.TEXT, defaultValue = "-1（无限制）")
    private Long expires;

    @Override
    public void init() {
        if (domain == null) {
            domain = "$common";
        }
        if (expires == null) {
            expires = -1L;
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
