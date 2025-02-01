package com.parch.combine.html.old.base.template;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.template.core.DomConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;


public interface PanelElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "title", name = "标题DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig title();

        @Field(key = "body", name = "内容DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig body();
    }
}
