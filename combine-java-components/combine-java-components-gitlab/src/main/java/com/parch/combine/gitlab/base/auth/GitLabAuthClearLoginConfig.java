package com.parch.combine.gitlab.base.auth;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface GitLabAuthClearLoginConfig extends ILogicConfig {

    @Field(key = "key", name = "缓存KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();
}
