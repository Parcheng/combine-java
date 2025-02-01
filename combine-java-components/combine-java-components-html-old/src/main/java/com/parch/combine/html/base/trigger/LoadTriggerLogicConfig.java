package com.parch.combine.html.base.trigger;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;

public interface LoadTriggerLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "触发配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
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
