package com.parch.combine.gitlab.base.project.hook;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabProjectHookDeleteLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "hookId", name = "Hook配置ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer hookId();
}
