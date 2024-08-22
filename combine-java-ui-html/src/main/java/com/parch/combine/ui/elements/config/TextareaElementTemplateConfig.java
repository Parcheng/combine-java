package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class TextareaElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "textarea", name = "多行文本框DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig textarea;

    public DomConfig getTextarea() {
        return textarea;
    }

    public void setTextarea(DomConfig textarea) {
        this.textarea = textarea;
    }
}
