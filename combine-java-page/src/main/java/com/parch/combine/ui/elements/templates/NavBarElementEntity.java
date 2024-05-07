package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.NavBarSettings;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "导航条页面元素", desc = "当 TYPE = NAV_BAR 时的参数列表")
public class NavBarElementEntity extends ElementEntity<NavBarSettings> {

    @Field(key = "navBar", name = "导航条DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig navBar;

    @Field(key = "brand", name = "导航条商标DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brand;

    @Field(key = "brandContent", name = "导航条商标内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brandContent;

    @Field(key = "brandImgContent", name = "导航条商标图片内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brandImgContent;

    @Field(key = "brandTextContent", name = "导航条商标文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig brandTextContent;

    @Field(key = "body", name = "导航条内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig body;

    @Field(key = "bodyNav", name = "导航DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNav;

    @Field(key = "bodyNavItem", name = "导航项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItem;

    @Field(key = "bodyNavItemText", name = "导航项文本DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItemText;

    @Field(key = "bodyNavItemTextActive", name = "导航项文本选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItemTextActive;

    @Field(key = "bodyNavItemChildren", name = "导航项子项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyNavItemChildren;

    @Field(key = "bodyRight", name = "导航右栏DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyRight;

    @Field(key = "bodyRightItem", name = "导航右栏项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyRightItem;

    @Field(key = "bodyRightItemButton", name = "导航右栏项中按钮DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyRightItemButton;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = NavBarSettings.class)
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
