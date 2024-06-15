package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.settings.PageSettingCanstant;

public class ListElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "list", name = "列表DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig list;

    @Field(key = "item", name = "列表项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig item;

    @Field(key = "defaultText", name = "列表为空时显示文本的DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig defaultText;

    public DomConfig getList() {
        return list;
    }

    public void setList(DomConfig list) {
        this.list = list;
    }

    public DomConfig getItem() {
        return item;
    }

    public void setItem(DomConfig item) {
        this.item = item;
    }

    public DomConfig getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(DomConfig defaultText) {
        this.defaultText = defaultText;
    }
}
