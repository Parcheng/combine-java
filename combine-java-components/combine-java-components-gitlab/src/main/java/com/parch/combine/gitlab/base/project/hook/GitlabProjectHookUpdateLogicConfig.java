package com.parch.combine.gitlab.base.project.hook;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabProjectSubLogicConfig;

public interface GitlabProjectHookUpdateLogicConfig extends GitlabProjectSubLogicConfig {

    @Field(key = "hookId", name = "Hook配置ID", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer hookId();

    @Field(key = "url", name = "推送地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String url();

    @Field(key = "doPushEvents", name = "是否触发推送事件", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean doPushEvents();

    @Field(key = "doIssuesEvents", name = "是否触发问题事件", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean doIssuesEvents();

    @Field(key = "doMergeRequestsEvents", name = "是否触发合并请求事件", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean doMergeRequestsEvents();
}
