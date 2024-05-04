package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.InputSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "输入框页面元素", desc = "当 TYPE = INPUT 时的参数列表")
public class InputElementEntity extends ElementEntity<InputSettings> {

    @ComponentField(key = "addon", name = "文本插件DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig addon;

    @ComponentField(key = "input", name = "文本输入框DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig input;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = InputSettings.class)
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
