package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.RadioSettings;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "单选输入框页面元素", desc = "当 TYPE = RADIO 时的参数列表")
public class RadioElementEntity extends ElementEntity<RadioSettings> {

    @Field(key = "inline", name = "内联显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig inline;

    @Field(key = "multiline", name = "多行显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig multiline;

    @Field(key = "disabled", name = "禁用时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig disabled;

    @Field(key = "option", name = "选项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig option;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = RadioSettings.class)
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
