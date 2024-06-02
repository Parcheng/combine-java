package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class TabElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "tab", name = "页签DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig tab;

    @Field(key = "item", name = "页签项DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig item;

    @Field(key = "itemActive", name = "页签项选中时DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemActive;

    @Field(key = "title", name = "页签项标题DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig title;

    @Field(key = "titleText", name = "页签项标题文本DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig titleText;

    @Field(key = "titleClose", name = "页签项标题关闭标识DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig titleClose;

    @Field(key = "body", name = "页签内容DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig body;

    @Field(key = "bodyContent", name = "页签内容项DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyItem;

    public DomConfig getTab() {
        return tab;
    }

    public void setTab(DomConfig tab) {
        this.tab = tab;
    }

    public DomConfig getItem() {
        return item;
    }

    public void setItem(DomConfig item) {
        this.item = item;
    }

    public DomConfig getItemActive() {
        return itemActive;
    }

    public void setItemActive(DomConfig itemActive) {
        this.itemActive = itemActive;
    }

    public DomConfig getTitle() {
        return title;
    }

    public void setTitle(DomConfig title) {
        this.title = title;
    }

    public DomConfig getBody() {
        return body;
    }

    public void setBody(DomConfig body) {
        this.body = body;
    }

    public DomConfig getBodyItem() {
        return bodyItem;
    }

    public void setBodyItem(DomConfig bodyItem) {
        this.bodyItem = bodyItem;
    }

    public DomConfig getTitleText() {
        return titleText;
    }

    public void setTitleText(DomConfig titleText) {
        this.titleText = titleText;
    }

    public DomConfig getTitleClose() {
        return titleClose;
    }

    public void setTitleClose(DomConfig titleClose) {
        this.titleClose = titleClose;
    }
}
