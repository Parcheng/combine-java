package com.parch.combine.tool.base.cache.get;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface CacheGetInitConfig extends IInitConfig {

    @Field(key = "renewal", name = "是否重置有效期", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean renewal();
}
