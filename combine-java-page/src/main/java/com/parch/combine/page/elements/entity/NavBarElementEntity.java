package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.NavBarSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "导航条页面元素", desc = "当 TYPE = NAV_BAR 时的参数列表")
public class NavBarElementEntity extends ElementEntity<NavBarSettings> {

    @ComponentField(key = "navBar", name = "导航条DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig navBar;

    @ComponentField(key = "brand", name = "导航条商标DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brand;

    @ComponentField(key = "brandContent", name = "导航条商标内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brandContent;

    @ComponentField(key = "brandImgContent", name = "导航条商标图片内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brandImgContent;

    @ComponentField(key = "brandTextContent", name = "导航条商标文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brandTextContent;

    @ComponentField(key = "body", name = "导航条内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig body;

    @ComponentField(key = "bodyNav", name = "导航DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNav;

    @ComponentField(key = "bodyNavItem", name = "导航项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItem;

    @ComponentField(key = "bodyNavItemText", name = "导航项文本DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItemText;

    @ComponentField(key = "bodyNavItemTextActive", name = "导航项文本选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItemTextActive;

    @ComponentField(key = "bodyNavItemChildren", name = "导航项子项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItemChildren;

    @ComponentField(key = "bodyRight", name = "导航右栏DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyRight;

    @ComponentField(key = "bodyRightItem", name = "导航右栏项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyRightItem;

    @ComponentField(key = "bodyRightItemButton", name = "导航右栏项中按钮DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyRightItemButton;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = NavBarSettings.class)
    private NavBarSettings settings;

    public NavBarElementEntity() {
        super(ElementTypeEnum.NAV_BAR);
    }

    @Override
    public NavBarSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(NavBarSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getNavBar() {
        return navBar;
    }

    public void setNavBar(ElementDomConfig navBar) {
        this.navBar = navBar;
    }

    public ElementDomConfig getBrand() {
        return brand;
    }

    public void setBrand(ElementDomConfig brand) {
        this.brand = brand;
    }

    public ElementDomConfig getBrandContent() {
        return brandContent;
    }

    public void setBrandContent(ElementDomConfig brandContent) {
        this.brandContent = brandContent;
    }

    public ElementDomConfig getBody() {
        return body;
    }

    public void setBody(ElementDomConfig body) {
        this.body = body;
    }

    public ElementDomConfig getBodyNav() {
        return bodyNav;
    }

    public void setBodyNav(ElementDomConfig bodyNav) {
        this.bodyNav = bodyNav;
    }

    public ElementDomConfig getBodyNavItem() {
        return bodyNavItem;
    }

    public void setBodyNavItem(ElementDomConfig bodyNavItem) {
        this.bodyNavItem = bodyNavItem;
    }

    public ElementDomConfig getBodyRight() {
        return bodyRight;
    }

    public void setBodyRight(ElementDomConfig bodyRight) {
        this.bodyRight = bodyRight;
    }

    public ElementDomConfig getBodyRightItem() {
        return bodyRightItem;
    }

    public void setBodyRightItem(ElementDomConfig bodyRightItem) {
        this.bodyRightItem = bodyRightItem;
    }

    public ElementDomConfig getBodyRightItemButton() {
        return bodyRightItemButton;
    }

    public void setBodyRightItemButton(ElementDomConfig bodyRightItemButton) {
        this.bodyRightItemButton = bodyRightItemButton;
    }

    public ElementDomConfig getBrandImgContent() {
        return brandImgContent;
    }

    public void setBrandImgContent(ElementDomConfig brandImgContent) {
        this.brandImgContent = brandImgContent;
    }

    public ElementDomConfig getBrandTextContent() {
        return brandTextContent;
    }

    public void setBrandTextContent(ElementDomConfig brandTextContent) {
        this.brandTextContent = brandTextContent;
    }

    public ElementDomConfig getBodyNavItemText() {
        return bodyNavItemText;
    }

    public void setBodyNavItemText(ElementDomConfig bodyNavItemText) {
        this.bodyNavItemText = bodyNavItemText;
    }

    public ElementDomConfig getBodyNavItemChildren() {
        return bodyNavItemChildren;
    }

    public void setBodyNavItemChildren(ElementDomConfig bodyNavItemChildren) {
        this.bodyNavItemChildren = bodyNavItemChildren;
    }

    public ElementDomConfig getBodyNavItemTextActive() {
        return bodyNavItemTextActive;
    }

    public void setBodyNavItemTextActive(ElementDomConfig bodyNavItemTextActive) {
        this.bodyNavItemTextActive = bodyNavItemTextActive;
    }
}
