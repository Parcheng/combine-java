package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.settings.TextareaSettings;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "多行文本输入框页面元素", desc = "当 TYPE = TEXTAREA 时的参数列表")
public class TextareaElementEntity extends ElementEntity<TextareaSettings> {

    @ComponentField(key = "textarea", name = "多行文本框DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig textarea;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = TextareaSettings.class)
    private TextareaSettings settings;

    public TextareaElementEntity() {
        super(ElementTypeEnum.TEXTAREA);
    }

    @Override
    public TextareaSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(TextareaSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getTextarea() {
        return textarea;
    }

    public void setTextarea(ElementDomConfig textarea) {
        this.textarea = textarea;
    }
}
