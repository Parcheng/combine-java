package com.parch.combine.gitlab.base;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface GitlabLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "缓存KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();
}
