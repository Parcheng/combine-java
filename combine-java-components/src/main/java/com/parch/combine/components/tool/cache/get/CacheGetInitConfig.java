package com.parch.combine.components.tool.cache.get;

import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 初始化配置类
 */
public class CacheGetInitConfig extends InitConfig {

    @Field(key = "renewal", name = "是否重置有效期", type = FieldTypeEnum.NUMBER, defaultValue = "true")
    private Boolean renewal;

    @Override
    public void init() {
        if (renewal == null) {
            renewal = true;
        }
    }

    public Boolean getRenewal() {
        return renewal;
    }

    public void setRenewal(Boolean renewal) {
        this.renewal = renewal;
    }
}
