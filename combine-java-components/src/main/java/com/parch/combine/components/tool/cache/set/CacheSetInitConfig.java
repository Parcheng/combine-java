package com.parch.combine.components.tool.cache.set;

import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 初始化配置类
 */
public class CacheSetInitConfig extends InitConfig {

    @ComponentField(key = "domainCapacity", name = "缓存域初始容量（个）", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    private Integer domainCapacity;

    @ComponentField(key = "keyCapacity", name = "每个缓存域中KEY的初始容量（个）", type = FieldTypeEnum.NUMBER, defaultValue = "8")
    private Integer keyCapacity;

    @Override
    public void init() {
        if (domainCapacity == null) {
            domainCapacity = 1;
        }
        if (keyCapacity == null) {
            keyCapacity = 1;
        }
    }

    public Integer getDomainCapacity() {
        return domainCapacity;
    }

    public void setDomainCapacity(Integer domainCapacity) {
        this.domainCapacity = domainCapacity;
    }

    public Integer getKeyCapacity() {
        return keyCapacity;
    }

    public void setKeyCapacity(Integer keyCapacity) {
        this.keyCapacity = keyCapacity;
    }
}
