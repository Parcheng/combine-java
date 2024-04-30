package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.PageTurningSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class PageTurningElementEntity extends ElementEntity<PageTurningSettings> {

    private ElementDomConfig external;

    private ElementDomConfig pageTurning;

    private ElementDomConfig last;

    private ElementDomConfig next;

    private ElementDomConfig lastContent;

    private ElementDomConfig nextContent;

    public PageTurningElementEntity() {
        super(ElementTypeEnum.PAGE_TURNING);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getPageTurning() {
        return pageTurning;
    }

    public void setPageTurning(ElementDomConfig pageTurning) {
        this.pageTurning = pageTurning;
    }

    public ElementDomConfig getLast() {
        return last;
    }

    public void setLast(ElementDomConfig last) {
        this.last = last;
    }

    public ElementDomConfig getNext() {
        return next;
    }

    public void setNext(ElementDomConfig next) {
        this.next = next;
    }

    public ElementDomConfig getLastContent() {
        return lastContent;
    }

    public void setLastContent(ElementDomConfig lastContent) {
        this.lastContent = lastContent;
    }

    public ElementDomConfig getNextContent() {
        return nextContent;
    }

    public void setNextContent(ElementDomConfig nextContent) {
        this.nextContent = nextContent;
    }
}
