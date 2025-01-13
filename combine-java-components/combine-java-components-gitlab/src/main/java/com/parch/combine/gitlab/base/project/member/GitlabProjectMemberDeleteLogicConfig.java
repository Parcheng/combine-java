package com.parch.combine.gitlab.base.project.member;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabProjectMemberDeleteLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "memberId", name = "成员ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer memberId();
}
