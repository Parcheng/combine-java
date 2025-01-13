package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabMergeCancelLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "mergedRequestId", name = "合并请求ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer mergedRequestId();
}
