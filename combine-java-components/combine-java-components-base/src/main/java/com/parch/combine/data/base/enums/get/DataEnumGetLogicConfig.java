package com.parch.combine.data.base.enums.get;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

/**
 * 逻辑配置类
 */
public interface DataEnumGetLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();
}
