package com.parch.combine.html.base.template.core;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface ElementTemplateConfig {

    @Field(key = "external", name = "外部DOM配置", type = FieldTypeEnum.CONFIG)
    @FieldRef(DomConfig.class)
    DomConfig external();
}
