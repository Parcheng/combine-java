package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class InputElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "addon", name = "文本插件DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig addon;

    @Field(key = "input", name = "文本输入框DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig input;

    public DomConfig getAddon() {
        return addon;
    }

    public void setAddon(DomConfig addon) {
        this.addon = addon;
    }

    public DomConfig getInput() {
        return input;
    }

    public void setInput(DomConfig input) {
        this.input = input;
    }
}
