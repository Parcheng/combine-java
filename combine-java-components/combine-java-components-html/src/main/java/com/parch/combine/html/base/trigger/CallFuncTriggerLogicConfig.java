package com.parch.combine.html.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;

public interface CallFuncTriggerLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "触发配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends TriggerConfig {
        @Field(key = "elementId", name = "页面元素ID", type = FieldTypeEnum.TEXT, isRequired = true)
        String elementId();

        @Field(key = "name", name = "函数名称", type = FieldTypeEnum.TEXT, isRequired = true)
        String name();

        @Field(key = "params", name = "函数参数", type = FieldTypeEnum.ANY)
        Object params();
    }
}
