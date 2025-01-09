package com.parch.combine.gitlab.base.branch;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.gitlab.base.GitlabLogicConfig;
import com.parch.combine.gitlab.base.project.GitlabProjectGetLogicConfig;

public interface GitlabBranchLogicConfig extends GitlabLogicConfig {

    @Field(key = "projectName", name = "项目名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String projectName();
}
