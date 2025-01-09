package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GitlabMergeCreateLogicConfig extends GitlabMergeLogicConfig {

    @Field(key = "source", name = "源分支名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String source();

    @Field(key = "target", name = "目标分支名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String target();

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT, isRequired = true)
    String title();

    @Field(key = "desc", name = "描述", type = FieldTypeEnum.TEXT)
    String desc();

    @Field(key = "assigneeIds", name = "合并人ID集合", type = FieldTypeEnum.NUMBER, isArray = true, isRequired = true)
    Integer[] assigneeIds();

    @Field(key = "hasMerged", name = "是否直接合并", type = FieldTypeEnum.NUMBER, defaultValue = "false")
    Boolean hasMerged();
}
