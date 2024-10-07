package com.parch.combine.html.base.template;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;


public interface HRElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "hr", name = "线DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig hr();
    }
}
