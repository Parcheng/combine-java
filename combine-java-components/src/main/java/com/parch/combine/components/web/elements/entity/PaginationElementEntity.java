package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.PaginationSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "分页页面元素", desc = "当 TYPE = PAGINATION 时的参数列表")
public class PaginationElementEntity extends ElementEntity<PaginationSettings> {

    @ComponentField(key = "pagination", name = "分页DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig pagination;

    @ComponentField(key = "item", name = "分页项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @ComponentField(key = "itemActive", name = "分页项选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemActive;

    @ComponentField(key = "itemDisabled", name = "分页项不可选时DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemDisabled;

    @ComponentField(key = "itemContentFirst", name = "首页项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemContentFirst;

    @ComponentField(key = "itemContentEnd", name = "尾页项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemContentEnd;

    @ComponentField(key = "itemContentNum", name = "选页项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemContentNum;

    @ComponentField(key = "itemContentSkip", name = "跳转指定页DOM配置（暂不支持）", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemContentSkip;

    @ComponentField(key = "itemContentSkipInput", name = "跳转指定页输入框DOM配置（暂不支持）", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemContentSkipInput;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = PaginationSettings.class)
    private PaginationSettings settings;

    public PaginationElementEntity() {
        super(ElementTypeEnum.PAGINATION);
    }

    @Override
    public PaginationSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(PaginationSettings settings) {
        this.settings = settings;
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
