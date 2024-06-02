package com.parch.combine.components.tool.cache.set;

import com.parch.combine.components.tool.cache.CacheLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class CacheSetLogicConfig extends CacheLogicConfig {

    @Field(key = "value", name = "数据/数据引用", type = FieldTypeEnum.TEXT, isRequired = true)
    private String value;

    @Field(key = "expires", name = "有效期", type = FieldTypeEnum.TEXT, defaultValue = "-1（无限制）")
    private Long expires;

    @Override
    public void init() {
        super.init();
        if (expires == null) {
            expires = -1L;
        }
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
}
