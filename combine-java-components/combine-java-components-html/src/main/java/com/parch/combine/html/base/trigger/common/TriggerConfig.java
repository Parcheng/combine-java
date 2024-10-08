package com.parch.combine.html.base.trigger.common;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 事件触发配置
 */
public interface TriggerConfig {

    @Field(key = "event", name = "触发要监听的事件", type = FieldTypeEnum.TEXT, isRequired = true)
    String event();

    @Field(key = "success", name = "触发执行成功后要渲染的元素配置", type = FieldTypeEnum.COMPONENT)
    String success();

    @Field(key = "fail", name = "触发执行失败后要渲染的元素配置", type = FieldTypeEnum.COMPONENT)
    String fail();

    @Field(key = "error", name = "触发执行异常后要渲染的元素配置", type = FieldTypeEnum.COMPONENT)
    String error();
}
