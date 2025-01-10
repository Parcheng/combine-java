package com.parch.combine.gitlab.base.project;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabLogicConfig;

public interface GitlabProjectGetLogicConfig extends GitlabLogicConfig {

    @Field(key = "name", name = "项目名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String name();
}
