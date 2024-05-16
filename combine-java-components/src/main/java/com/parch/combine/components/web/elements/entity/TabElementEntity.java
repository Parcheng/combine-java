package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TabSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "页签页面元素", desc = "当 TYPE = TAB 时的参数列表")
public class TabElementEntity extends ElementEntity<TabSettings> {

    @Field(key = "tab", name = "页签DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig tab;

    @Field(key = "item", name = "页签项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @Field(key = "itemActive", name = "页签项选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemActive;

    @Field(key = "title", name = "页签项标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig title;

    @Field(key = "titleText", name = "页签项标题文本DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig titleText;

    @Field(key = "titleClose", name = "页签项标题关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig titleClose;

    @Field(key = "content", name = "页签内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = TabSettings.class)
    private TabSettings settings;

    public TabElementEntity() {
        super(ElementTypeEnum.TAB);
    }

    @Override
    public TabSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(TabSettings settings) {
        this.settings = settings;
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
