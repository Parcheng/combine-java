package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.TabSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class TabElementEntity extends ElementEntity<TabSettings> {

    private ElementDomConfig external;

    private ElementDomConfig tab;

    private ElementDomConfig item;

    private ElementDomConfig itemActive;

    private ElementDomConfig title;

    private ElementDomConfig titleText;

    private ElementDomConfig titleClose;

    private ElementDomConfig content;

    public TabElementEntity() {
        super(ElementTypeEnum.TAB);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getTab() {
        return tab;
    }

    public void setTab(ElementDomConfig tab) {
        this.tab = tab;
    }

    public ElementDomConfig getItem() {
        return item;
    }

    public void setItem(ElementDomConfig item) {
        this.item = item;
    }

    public ElementDomConfig getItemActive() {
        return itemActive;
    }

    public void setItemActive(ElementDomConfig itemActive) {
        this.itemActive = itemActive;
    }

    public ElementDomConfig getTitle() {
        return title;
    }

    public void setTitle(ElementDomConfig title) {
        this.title = title;
    }

    public ElementDomConfig getContent() {
        return content;
    }

    public void setContent(ElementDomConfig content) {
        this.content = content;
    }

    public ElementDomConfig getTitleText() {
        return titleText;
    }

    public void setTitleText(ElementDomConfig titleText) {
        this.titleText = titleText;
    }

    public ElementDomConfig getTitleClose() {
        return titleClose;
    }

    public void setTitleClose(ElementDomConfig titleClose) {
        this.titleClose = titleClose;
    }
}
