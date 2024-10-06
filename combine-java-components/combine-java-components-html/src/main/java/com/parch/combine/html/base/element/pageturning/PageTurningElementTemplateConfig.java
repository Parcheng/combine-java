package com.parch.combine.html.base.element.pageturning;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class PageTurningElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "pageTurning", name = "DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig pageTurning;

    @Field(key = "last", name = "向前翻页元素DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig last;

    @Field(key = "next", name = "向后翻页元素DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig next;

    @Field(key = "lastContent", name = "向前翻页文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig lastContent;

    @Field(key = "nextContent", name = "向后翻页文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig nextContent;

    public DomConfig getPageTurning() {
        return pageTurning;
    }

    public void setPageTurning(DomConfig pageTurning) {
        this.pageTurning = pageTurning;
    }

    public DomConfig getLast() {
        return last;
    }

    public void setLast(DomConfig last) {
        this.last = last;
    }

    public DomConfig getNext() {
        return next;
    }

    public void setNext(DomConfig next) {
        this.next = next;
    }

    public DomConfig getLastContent() {
        return lastContent;
    }

    public void setLastContent(DomConfig lastContent) {
        this.lastContent = lastContent;
    }

    public DomConfig getNextContent() {
        return nextContent;
    }

    public void setNextContent(DomConfig nextContent) {
        this.nextContent = nextContent;
    }
}
