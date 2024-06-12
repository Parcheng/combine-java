package com.parch.combine.components.data.enums.list;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public interface DataEnumGetLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();
}
