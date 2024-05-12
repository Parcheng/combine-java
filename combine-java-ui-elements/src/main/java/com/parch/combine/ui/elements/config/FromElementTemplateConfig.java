package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class FromElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "from", name = "表单DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig from;

    @Field(key = "item", name = "表单项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig item;

    @Field(key = "label", name = "标签DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig label;

    @Field(key = "content", name = "内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig content;

    public DomConfig getItem() {
        return item;
    }

    public void setItem(DomConfig item) {
        this.item = item;
    }

    public DomConfig getContent() {
        return content;
    }

    public void setContent(DomConfig content) {
        this.content = content;
    }

    public DomConfig getFrom() {
        return from;
    }

    public void setFrom(DomConfig from) {
        this.from = from;
    }

    public DomConfig getLabel() {
        return label;
    }

    public void setLabel(DomConfig label) {
        this.label = label;
    }
}
