package com.parch.combine.gitlab.base.branch;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface GitlabBranchGetLogicConfig extends GitlabBranchLogicConfig {

    @Field(key = "name", name = "分支名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String name();
}
