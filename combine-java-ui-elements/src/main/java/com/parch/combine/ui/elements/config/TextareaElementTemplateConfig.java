package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class TextareaElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "textarea", name = "多行文本框DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig textarea;

    public DomConfig getTextarea() {
        return textarea;
    }

    public void setTextarea(DomConfig textarea) {
        this.textarea = textarea;
    }
}
