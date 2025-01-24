package com.parch.combine.gitlab.base.project.member;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabProjectMemberGetLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "memberId", name = "成员ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer memberId();
}
