package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.PageTurningSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "翻页页面元素", desc = "当 TYPE = PAGE_TURNING 时的参数列表")
public class PageTurningElementEntity extends ElementEntity<PageTurningSettings> {

    @ComponentField(key = "pageTurning", name = "DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig pageTurning;

    @ComponentField(key = "last", name = "向前翻页元素DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig last;

    @ComponentField(key = "next", name = "向后翻页元素DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig next;

    @ComponentField(key = "lastContent", name = "向前翻页文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig lastContent;

    @ComponentField(key = "nextContent", name = "向后翻页文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig nextContent;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = PageTurningSettings.class)
    private PageTurningSettings settings;

    public PageTurningElementEntity() {
        super(ElementTypeEnum.PAGE_TURNING);
    }

    @Override
    public PageTurningSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(PageTurningSettings settings) {
        this.settings = settings;
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
