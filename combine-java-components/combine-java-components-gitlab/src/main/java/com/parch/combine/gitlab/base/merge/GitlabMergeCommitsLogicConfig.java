package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabMergeCommitsLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "mergedRequestId", name = "合并请求ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer mergedRequestId();

    @Field(key = "page", name = "页码", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    Integer page();

    @Field(key = "pageSize", name = "每页条数", type = FieldTypeEnum.NUMBER, defaultValue = "20")
    Integer pageSize();
}
