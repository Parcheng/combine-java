package com.parch.combine.gitlab.base.project.member;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabProjectMemberDeleteLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "userId", name = "用户ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer userId();
}
