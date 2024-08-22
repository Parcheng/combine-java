package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

/**
 * 配置类
 */
public class BreadcrumbElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "breadcrumb", name = "面包削DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig breadcrumb;

    @Field(key = "itemActive", name = "选中元素项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig itemActive;

    @Field(key = "content", name = "元素项内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig content;

    @Field(key = "item", name = "元素项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig item;

    public DomConfig getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(DomConfig breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public DomConfig getItemActive() {
        return itemActive;
    }

    public void setItemActive(DomConfig itemActive) {
        this.itemActive = itemActive;
    }

    public DomConfig getContent() {
        return content;
    }

    public void setContent(DomConfig content) {
        this.content = content;
    }

    public DomConfig getItem() {
        return item;
    }

    public void setItem(DomConfig item) {
        this.item = item;
    }
}
