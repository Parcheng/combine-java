package com.parch.combine.gitlab.base.project.member;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

import java.util.Date;

public interface GitlabProjectMemberAddLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "userId", name = "用户ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer userId();

    @Field(key = "accessLevel", name = "访问级别", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = ProjectMemberAccessLevelEnum.class)
    String accessLevel();

    @Field(key = "expire", name = "有效期（秒）", type = FieldTypeEnum.SELECT, defaultValue = "-1")
    @FieldDesc("不设置则默认不限期")
    @FieldSelect(enumClass = ProjectMemberAccessLevelEnum.class)
    Long expire();
}
