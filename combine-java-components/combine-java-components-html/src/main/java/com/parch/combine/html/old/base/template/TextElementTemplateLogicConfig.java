package com.parch.combine.html.old.base.template;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.template.core.DomConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;


public interface TextElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "line", name = "行通用DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig line();

        @Field(key = "children", name = "子文本DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig children();

        @Field(key = "lines", name = "每个层级的通用DOM配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldRef(DomConfig.class)
        DomConfig[] lines();

        @Field(key = "levels", name = "每行的DOM配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldRef(DomConfig.class)
        DomConfig[] levels();
    }
}
