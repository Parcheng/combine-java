package com.parch.combine.html.old.base.template;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.template.core.DomConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;


/**
 * 配置类
 */
public interface BreadcrumbElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "breadcrumb", name = "面包削DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig breadcrumb();

        @Field(key = "itemActive", name = "选中元素项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemActive();

        @Field(key = "content", name = "元素项内容DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig content();

        @Field(key = "item", name = "元素项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig item();
    }
}
