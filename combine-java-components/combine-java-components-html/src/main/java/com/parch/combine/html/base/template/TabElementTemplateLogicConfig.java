package com.parch.combine.html.base.template;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.template.core.DomConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;


public interface TabElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "tab", name = "页签DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig tab();

        @Field(key = "item", name = "页签项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig item();

        @Field(key = "itemActive", name = "页签项选中时DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemActive();

        @Field(key = "title", name = "页签项标题DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig title();

        @Field(key = "titleText", name = "页签项标题文本DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig titleText();

        @Field(key = "titleClose", name = "页签项标题关闭标识DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig titleClose();

        @Field(key = "body", name = "页签内容DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig body();

        @Field(key = "bodyContent", name = "页签内容项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig bodyItem();
    }
}
