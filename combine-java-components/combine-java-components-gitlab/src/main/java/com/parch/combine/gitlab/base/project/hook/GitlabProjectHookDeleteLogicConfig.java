package com.parch.combine.gitlab.base.project.hook;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabLogicConfig;

public interface GitlabProjectHookDeleteLogicConfig extends GitlabLogicConfig {

    @Field(key = "idOrName", name = "项目名称或ID", type = FieldTypeEnum.ANY, isRequired = true)
    Object idOrName();

    @Field(key = "hookId", name = "Hook配置ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer hookId();
}
