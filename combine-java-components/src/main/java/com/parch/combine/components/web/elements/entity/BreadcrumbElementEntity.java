package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.BreadcrumbSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "面包削页面元素", desc = "当 TYPE = BREADCRUMB 时的参数列表")
public class BreadcrumbElementEntity extends ElementEntity<BreadcrumbSettings> {

    @Field(key = "breadcrumb", name = "面包削DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig breadcrumb;

    @Field(key = "itemActive", name = "选中元素项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemActive;

    @Field(key = "content", name = "元素项内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @Field(key = "item", name = "元素项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @Field(key = "settings", name = "元素配置（暂无）", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = BreadcrumbSettings.class)
    private BreadcrumbSettings settings;

    public BreadcrumbElementEntity() {
        super(ElementTypeEnum.BREADCRUMB);
    }

    @Override
    public BreadcrumbSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(BreadcrumbSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getItem() {
        return item;
    }

    public void setItem(ElementDomConfig item) {
        this.item = item;
    }

    public ElementDomConfig getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(ElementDomConfig breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public ElementDomConfig getItemActive() {
        return itemActive;
    }

    public void setItemActive(ElementDomConfig itemActive) {
        this.itemActive = itemActive;
    }

    public ElementDomConfig getContent() {
        return content;
    }

    public void setContent(ElementDomConfig content) {
        this.content = content;
    }
}
