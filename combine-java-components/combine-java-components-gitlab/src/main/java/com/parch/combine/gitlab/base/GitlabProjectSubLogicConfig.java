package com.parch.combine.gitlab.base;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface GitlabProjectSubLogicConfig extends GitlabLogicConfig {

    @Field(key = "projectIdOrName", name = "项目名称或ID", type = FieldTypeEnum.ANY, isRequired = true)
    Object projectIdOrName();
}
