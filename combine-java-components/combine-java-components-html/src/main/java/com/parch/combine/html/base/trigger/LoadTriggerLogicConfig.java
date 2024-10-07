package com.parch.combine.html.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface LoadTriggerLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "触发配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends TriggerConfig {

        @Field(key = "groupId", name = "元素组ID", type = FieldTypeEnum.TEXT, isRequired = true)
        String groupId();

        @Field(key = "parentId", name = "要写入到的父元素DOM ID", type = FieldTypeEnum.TEXT, isRequired = true)
        String parentId();

        @Field(key = "params", name = "加载元素组的数据", type = FieldTypeEnum.ANY)
        Object params();

    }
}
