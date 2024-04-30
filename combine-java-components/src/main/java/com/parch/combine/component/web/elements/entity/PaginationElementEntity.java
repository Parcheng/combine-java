package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.PaginationSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class PaginationElementEntity extends ElementEntity<PaginationSettings> {

    private ElementDomConfig external;

    private ElementDomConfig pagination;

    private ElementDomConfig item;

    private ElementDomConfig itemActive;

    private ElementDomConfig itemDisabled;

    private ElementDomConfig itemContentFirst;

    private ElementDomConfig itemContentEnd;

    private ElementDomConfig itemContentNum;

    private ElementDomConfig itemContentSkip;

    private ElementDomConfig itemContentSkipInput;

    public PaginationElementEntity() {
        super(ElementTypeEnum.PAGINATION);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getPagination() {
        return pagination;
    }

    public void setPagination(ElementDomConfig pagination) {
        this.pagination = pagination;
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

    public ElementDomConfig getItemDisabled() {
        return itemDisabled;
    }

    public void setItemDisabled(ElementDomConfig itemDisabled) {
        this.itemDisabled = itemDisabled;
    }

    public ElementDomConfig getItemContentFirst() {
        return itemContentFirst;
    }

    public void setItemContentFirst(ElementDomConfig itemContentFirst) {
        this.itemContentFirst = itemContentFirst;
    }

    public ElementDomConfig getItemContentEnd() {
        return itemContentEnd;
    }

    public void setItemContentEnd(ElementDomConfig itemContentEnd) {
        this.itemContentEnd = itemContentEnd;
    }

    public ElementDomConfig getItemContentNum() {
        return itemContentNum;
    }

    public void setItemContentNum(ElementDomConfig itemContentNum) {
        this.itemContentNum = itemContentNum;
    }

    public ElementDomConfig getItemContentSkip() {
        return itemContentSkip;
    }

    public void setItemContentSkip(ElementDomConfig itemContentSkip) {
        this.itemContentSkip = itemContentSkip;
    }

    public ElementDomConfig getItemContentSkipInput() {
        return itemContentSkipInput;
    }

    public void setItemContentSkipInput(ElementDomConfig itemContentSkipInput) {
        this.itemContentSkipInput = itemContentSkipInput;
    }
}
