package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GitlabMergeApprovalRulesLogicConfig extends GitlabMergeLogicConfig {

    @Field(key = "mergedRequestId", name = "合并请求ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer mergedRequestId();
}
