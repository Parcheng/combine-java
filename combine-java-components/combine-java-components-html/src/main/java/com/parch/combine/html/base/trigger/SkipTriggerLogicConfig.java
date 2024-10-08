package com.parch.combine.html.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;

public interface SkipTriggerLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "触发配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends TriggerConfig {

        @Field(key = "url", name = "要跳转的URL", type = FieldTypeEnum.TEXT, isRequired = true)
        String url();
    }
}
