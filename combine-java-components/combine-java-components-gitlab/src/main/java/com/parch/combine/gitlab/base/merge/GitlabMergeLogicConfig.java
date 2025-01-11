package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabLogicConfig;

public interface GitlabMergeLogicConfig extends GitlabLogicConfig {

    @Field(key = "projectIdOrName", name = "项目名称或ID", type = FieldTypeEnum.ANY, isRequired = true)
    Object projectIdOrName();
}
