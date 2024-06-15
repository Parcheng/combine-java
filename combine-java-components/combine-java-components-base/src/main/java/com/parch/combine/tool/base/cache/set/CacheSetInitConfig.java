package com.parch.combine.tool.base.cache.set;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface CacheSetInitConfig extends IInitConfig {

    @Field(key = "domainCapacity", name = "缓存域初始容量（个）", type = FieldTypeEnum.NUMBER, defaultValue = "8")
    Integer domainCapacity();

    @Field(key = "keyCapacity", name = "每个缓存域中KEY的初始容量（个）", type = FieldTypeEnum.NUMBER, defaultValue = "8")
    Integer keyCapacity();
}
