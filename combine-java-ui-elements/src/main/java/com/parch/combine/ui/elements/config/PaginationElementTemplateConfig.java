package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class PaginationElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "pagination", name = "分页DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig pagination;

    @Field(key = "item", name = "分页项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig item;

    @Field(key = "itemActive", name = "分页项选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemActive;

    @Field(key = "itemDisabled", name = "分页项不可选时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemDisabled;

    @Field(key = "itemContentFirst", name = "首页项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemContentFirst;

    @Field(key = "itemContentEnd", name = "尾页项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemContentEnd;

    @Field(key = "itemContentNum", name = "选页项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemContentNum;

    @Field(key = "itemContentSkip", name = "跳转指定页DOM配置（暂不支持）", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemContentSkip;

    @Field(key = "itemContentSkipInput", name = "跳转指定页输入框DOM配置（暂不支持）", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemContentSkipInput;

    public DomConfig getPagination() {
        return pagination;
    }

    public void setPagination(DomConfig pagination) {
        this.pagination = pagination;
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

    public DomConfig getItemDisabled() {
        return itemDisabled;
    }

    public void setItemDisabled(DomConfig itemDisabled) {
        this.itemDisabled = itemDisabled;
    }

    public DomConfig getItemContentFirst() {
        return itemContentFirst;
    }

    public void setItemContentFirst(DomConfig itemContentFirst) {
        this.itemContentFirst = itemContentFirst;
    }

    public DomConfig getItemContentEnd() {
        return itemContentEnd;
    }

    public void setItemContentEnd(DomConfig itemContentEnd) {
        this.itemContentEnd = itemContentEnd;
    }

    public DomConfig getItemContentNum() {
        return itemContentNum;
    }

    public void setItemContentNum(DomConfig itemContentNum) {
        this.itemContentNum = itemContentNum;
    }

    public DomConfig getItemContentSkip() {
        return itemContentSkip;
    }

    public void setItemContentSkip(DomConfig itemContentSkip) {
        this.itemContentSkip = itemContentSkip;
    }

    public DomConfig getItemContentSkipInput() {
        return itemContentSkipInput;
    }

    public void setItemContentSkipInput(DomConfig itemContentSkipInput) {
        this.itemContentSkipInput = itemContentSkipInput;
    }
}
