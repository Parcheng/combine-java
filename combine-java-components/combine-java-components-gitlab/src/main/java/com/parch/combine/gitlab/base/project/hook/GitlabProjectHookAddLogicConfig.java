package com.parch.combine.gitlab.base.project.hook;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabProjectHookAddLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "ifNotExist", name = "如果不存在", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean ifNotExist();

    @Field(key = "url", name = "推送地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String url();

    @Field(key = "doPushEvents", name = "是否触发推送事件", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean doPushEvents();

    @Field(key = "doIssuesEvents", name = "是否触发问题事件", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean doIssuesEvents();

    @Field(key = "doMergeRequestsEvents", name = "是否触发合并请求事件", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean doMergeRequestsEvents();
}
