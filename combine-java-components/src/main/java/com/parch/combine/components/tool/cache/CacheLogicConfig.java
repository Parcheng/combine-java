package com.parch.combine.components.tool.cache;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.old.LogicConfig;

/**
 * 逻辑配置类
 */
public class CacheLogicConfig extends ILogicConfig {

    @Field(key = "domain", name = "缓存域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    private String domain;

    @Field(key = "key", name = "缓存KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @Override
    public void init() {
        if (domain == null) {
            domain = "$common";
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
