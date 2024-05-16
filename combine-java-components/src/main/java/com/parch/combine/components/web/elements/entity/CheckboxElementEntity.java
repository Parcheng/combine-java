package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.CheckboxSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "多选输入框页面元素", desc = "当 TYPE = CHECKBOX 时的参数列表")
public class CheckboxElementEntity extends ElementEntity<CheckboxSettings> {

    @Field(key = "inline", name = "内联显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig inline;

    @Field(key = "multiline", name = "多行显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig multiline;

    @Field(key = "disabled", name = "禁用时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig disabled;

    @Field(key = "option", name = "每个选项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig option;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = CheckboxSettings.class)
    private CheckboxSettings settings;

    public CheckboxElementEntity() {
        super(ElementTypeEnum.CHECKBOX);
    }

    @Override
    public CheckboxSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(CheckboxSettings settings) {
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
