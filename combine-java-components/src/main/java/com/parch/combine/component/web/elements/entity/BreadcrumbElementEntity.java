package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.BreadcrumbSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class BreadcrumbElementEntity extends ElementEntity<BreadcrumbSettings> {

    private ElementDomConfig external;

    private ElementDomConfig breadcrumb;

    private ElementDomConfig itemActive;

    private ElementDomConfig content;

    private ElementDomConfig item;

    public BreadcrumbElementEntity() {
        super(ElementTypeEnum.BREADCRUMB);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
