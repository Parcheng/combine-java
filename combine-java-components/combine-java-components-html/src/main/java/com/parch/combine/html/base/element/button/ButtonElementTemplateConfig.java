package com.parch.combine.html.base.element.button;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class ButtonElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "button", name = "按钮DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig button;

    public DomConfig getButton() {
        return button;
    }

    public void setButton(DomConfig button) {
        this.button = button;
    }
}