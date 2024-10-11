package com.parch.combine.html.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;

import java.util.List;

public interface CustomTriggerLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "触发配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends TriggerConfig {

        @Field(key = "functionName", name = "自定义函数名", type = FieldTypeEnum.TEXT, isRequired = true)
        String functionName();

        @Field(key = "functionParams", name = "自定义函数参数", type = FieldTypeEnum.ANY, isArray = true)
        Object[] functionParams();

        @Field(key = "toLocalStorage", name = "是否将结果保存到浏览器本地存储", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        @FieldDesc("缓存KEY为当前“Trigger配置ID”")
        Boolean toLocalStorage();
    }
}
