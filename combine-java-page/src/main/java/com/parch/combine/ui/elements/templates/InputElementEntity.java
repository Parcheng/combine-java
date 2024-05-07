package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.InputSettings;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "输入框页面元素", desc = "当 TYPE = INPUT 时的参数列表")
public class InputElementEntity extends ElementEntity<InputSettings> {

    @Field(key = "addon", name = "文本插件DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig addon;

    @Field(key = "input", name = "文本输入框DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig input;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = InputSettings.class)
    private InputSettings settings;

    public InputElementEntity() {
        super(ElementTypeEnum.INPUT);
    }

    @Override
    public InputSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(InputSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getAddon() {
        return addon;
    }

    public void setAddon(ElementDomConfig addon) {
        this.addon = addon;
    }

    public ElementDomConfig getInput() {
        return input;
    }

    public void setInput(ElementDomConfig input) {
        this.input = input;
    }
}
