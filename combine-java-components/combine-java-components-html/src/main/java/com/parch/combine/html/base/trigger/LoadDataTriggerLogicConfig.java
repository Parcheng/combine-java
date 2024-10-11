package com.parch.combine.html.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;

public interface LoadDataTriggerLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "触发配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends TriggerConfig {

        @Field(key = "loadIds", name = "要加载的LOAD ID集合", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        String[] loadIds();
    }
}
