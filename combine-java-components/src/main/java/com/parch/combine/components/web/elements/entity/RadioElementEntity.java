package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.RadioSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "单选输入框页面元素", desc = "当 TYPE = RADIO 时的参数列表")
public class RadioElementEntity extends ElementEntity<RadioSettings> {

    @ComponentField(key = "inline", name = "内联显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig inline;

    @ComponentField(key = "multiline", name = "多行显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig multiline;

    @ComponentField(key = "disabled", name = "禁用时DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig disabled;

    @ComponentField(key = "option", name = "选项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig option;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = RadioSettings.class)
    private RadioSettings settings;

    public RadioElementEntity() {
        super(ElementTypeEnum.RADIO);
    }

    @Override
    public RadioSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(RadioSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getInline() {
        return inline;
    }

    public void setInline(ElementDomConfig inline) {
        this.inline = inline;
    }

    public ElementDomConfig getMultiline() {
        return multiline;
    }

    public void setMultiline(ElementDomConfig multiline) {
        this.multiline = multiline;
    }

    public ElementDomConfig getDisabled() {
        return disabled;
    }

    public void setDisabled(ElementDomConfig disabled) {
        this.disabled = disabled;
    }

    public ElementDomConfig getOption() {
        return option;
    }

    public void setOption(ElementDomConfig option) {
        this.option = option;
    }
}
