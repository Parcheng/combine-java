package com.parch.combine.html.old.base.template.core;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface ElementTemplateConfig {

    @Field(key = "external", name = "外部DOM配置", type = FieldTypeEnum.CONFIG)
    @FieldRef(DomConfig.class)
    DomConfig external();
}
