package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.PageTurningSettings;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "翻页页面元素", desc = "当 TYPE = PAGE_TURNING 时的参数列表")
public class PageTurningElementEntity extends ElementEntity<PageTurningSettings> {

    @Field(key = "pageTurning", name = "DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig pageTurning;

    @Field(key = "last", name = "向前翻页元素DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig last;

    @Field(key = "next", name = "向后翻页元素DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig next;

    @Field(key = "lastContent", name = "向前翻页文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig lastContent;

    @Field(key = "nextContent", name = "向后翻页文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig nextContent;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = PageTurningSettings.class)
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
