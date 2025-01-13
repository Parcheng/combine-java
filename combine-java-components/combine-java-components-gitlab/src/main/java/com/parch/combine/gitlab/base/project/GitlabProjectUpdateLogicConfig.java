package com.parch.combine.gitlab.base.project;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabLogicConfig;
import com.parch.combine.gitlab.base.project.hook.GitlabProjectVisibilityEnum;

public interface GitlabProjectUpdateLogicConfig extends GitlabLogicConfig {

    @Field(key = "projectIdOrName", name = "项目名称或ID", type = FieldTypeEnum.ANY, isRequired = true)
    Object idOrName();

    @Field(key = "name", name = "项目名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String name();

    @Field(key = "desc", name = "项目描述", type = FieldTypeEnum.TEXT)
    String desc();

    @Field(key = "namespaceId", name = "命名空间ID", type = FieldTypeEnum.NUMBER)
    Integer namespaceId();

    @Field(key = "visibility", name = "可见性", type = FieldTypeEnum.SELECT, defaultValue = "PUBLIC")
    @FieldSelect(enumClass = GitlabProjectVisibilityEnum.class)
    String visibility();
}
