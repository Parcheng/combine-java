package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class NavBarElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "navBar", name = "导航条DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig navBar;

    @Field(key = "brand", name = "导航条商标DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig brand;

    @Field(key = "brandContent", name = "导航条商标内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig brandContent;

    @Field(key = "brandImgContent", name = "导航条商标图片内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig brandImgContent;

    @Field(key = "brandTextContent", name = "导航条商标文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig brandTextContent;

    @Field(key = "body", name = "导航条内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig body;

    @Field(key = "bodyNav", name = "导航DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyNav;

    @Field(key = "bodyNavItem", name = "导航项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyNavItem;

    @Field(key = "bodyNavItemText", name = "导航项文本DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyNavItemText;

    @Field(key = "bodyNavItemTextActive", name = "导航项文本选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyNavItemTextActive;

    @Field(key = "bodyNavItemChildren", name = "导航项子项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyNavItemChildren;

    @Field(key = "bodyRight", name = "导航右栏DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyRight;

    @Field(key = "bodyRightItem", name = "导航右栏项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyRightItem;

    @Field(key = "bodyRightItemButton", name = "导航右栏项中按钮DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyRightItemButton;

    public DomConfig getNavBar() {
        return navBar;
    }

    public void setNavBar(DomConfig navBar) {
        this.navBar = navBar;
    }

    public DomConfig getBrand() {
        return brand;
    }

    public void setBrand(DomConfig brand) {
        this.brand = brand;
    }

    public DomConfig getBrandContent() {
        return brandContent;
    }

    public void setBrandContent(DomConfig brandContent) {
        this.brandContent = brandContent;
    }

    public DomConfig getBody() {
        return body;
    }

    public void setBody(DomConfig body) {
        this.body = body;
    }

    public DomConfig getBodyNav() {
        return bodyNav;
    }

    public void setBodyNav(DomConfig bodyNav) {
        this.bodyNav = bodyNav;
    }

    public DomConfig getBodyNavItem() {
        return bodyNavItem;
    }

    public void setBodyNavItem(DomConfig bodyNavItem) {
        this.bodyNavItem = bodyNavItem;
    }

    public DomConfig getBodyRight() {
        return bodyRight;
    }

    public void setBodyRight(DomConfig bodyRight) {
        this.bodyRight = bodyRight;
    }

    public DomConfig getBodyRightItem() {
        return bodyRightItem;
    }

    public void setBodyRightItem(DomConfig bodyRightItem) {
        this.bodyRightItem = bodyRightItem;
    }

    public DomConfig getBodyRightItemButton() {
        return bodyRightItemButton;
    }

    public void setBodyRightItemButton(DomConfig bodyRightItemButton) {
        this.bodyRightItemButton = bodyRightItemButton;
    }

    public DomConfig getBrandImgContent() {
        return brandImgContent;
    }

    public void setBrandImgContent(DomConfig brandImgContent) {
        this.brandImgContent = brandImgContent;
    }

    public DomConfig getBrandTextContent() {
        return brandTextContent;
    }

    public void setBrandTextContent(DomConfig brandTextContent) {
        this.brandTextContent = brandTextContent;
    }

    public DomConfig getBodyNavItemText() {
        return bodyNavItemText;
    }

    public void setBodyNavItemText(DomConfig bodyNavItemText) {
        this.bodyNavItemText = bodyNavItemText;
    }

    public DomConfig getBodyNavItemChildren() {
        return bodyNavItemChildren;
    }

    public void setBodyNavItemChildren(DomConfig bodyNavItemChildren) {
        this.bodyNavItemChildren = bodyNavItemChildren;
    }

    public DomConfig getBodyNavItemTextActive() {
        return bodyNavItemTextActive;
    }

    public void setBodyNavItemTextActive(DomConfig bodyNavItemTextActive) {
        this.bodyNavItemTextActive = bodyNavItemTextActive;
    }
}
