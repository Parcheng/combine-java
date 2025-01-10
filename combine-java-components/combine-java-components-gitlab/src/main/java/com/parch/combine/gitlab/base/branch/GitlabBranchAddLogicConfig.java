package com.parch.combine.gitlab.base.branch;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GitlabBranchAddLogicConfig extends GitlabBranchLogicConfig {

    @Field(key = "name", name = "分支名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String name();

    @Field(key = "source", name = "来源分支名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String source();
}
