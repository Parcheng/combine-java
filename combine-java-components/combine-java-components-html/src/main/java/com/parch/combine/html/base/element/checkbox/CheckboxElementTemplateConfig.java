package com.parch.combine.html.base.element.checkbox;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class CheckboxElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "inline", name = "内联显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig inline;

    @Field(key = "multiline", name = "多行显示时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig multiline;

    @Field(key = "disabled", name = "禁用时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig disabled;

    @Field(key = "option", name = "每个选项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig option;

    public DomConfig getInline() {
        return inline;
    }

    public void setInline(DomConfig inline) {
        this.inline = inline;
    }

    public DomConfig getMultiline() {
        return multiline;
    }

    public void setMultiline(DomConfig multiline) {
        this.multiline = multiline;
    }

    public DomConfig getDisabled() {
        return disabled;
    }

    public void setDisabled(DomConfig disabled) {
        this.disabled = disabled;
    }

    public DomConfig getOption() {
        return option;
    }

    public void setOption(DomConfig option) {
        this.option = option;
    }
}
