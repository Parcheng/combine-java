package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabMergeDoLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "mergedRequestId", name = "合并请求ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer mergedRequestId();
}
