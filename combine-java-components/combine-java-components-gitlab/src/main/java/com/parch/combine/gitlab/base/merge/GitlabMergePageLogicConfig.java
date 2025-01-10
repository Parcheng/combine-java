package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GitlabMergePageLogicConfig extends GitlabMergeLogicConfig {

    @Field(key = "state", name = "状态", type = FieldTypeEnum.SELECT, defaultValue = "ALL")
    @FieldSelect(enumClass = MergeRequestStateEnum.class)
    String state();

    @Field(key = "page", name = "页码", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    Integer page();

    @Field(key = "pageSize", name = "每页条数", type = FieldTypeEnum.NUMBER, defaultValue = "20")
    Integer pageSize();
}