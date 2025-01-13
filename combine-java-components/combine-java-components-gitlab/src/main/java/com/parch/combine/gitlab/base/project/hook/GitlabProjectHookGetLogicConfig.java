package com.parch.combine.gitlab.base.project.hook;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabProjectHookGetLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "hookId", name = "Hook配置ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer hookId();
}
