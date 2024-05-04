package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.ButtonSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "按钮页面元素", desc = "当 TYPE = BUTTON 时的参数列表")
public class ButtonElementEntity extends ElementEntity<ButtonSettings> {

    @ComponentField(key = "button", name = "按钮DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig button;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = ButtonSettings.class)
    private ButtonSettings settings;

    @Override
    public ButtonSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(ButtonSettings settings) {
        this.settings = settings;
    }

    public ButtonElementEntity() {
        super(ElementTypeEnum.BUTTON);
    }

    public ElementDomConfig getButton() {
        return button;
    }

    public void setButton(ElementDomConfig button) {
        this.button = button;
    }
}