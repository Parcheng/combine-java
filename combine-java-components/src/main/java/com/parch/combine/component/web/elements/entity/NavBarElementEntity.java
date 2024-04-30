package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.NavBarSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class NavBarElementEntity extends ElementEntity<NavBarSettings> {

    private ElementDomConfig external;

    private ElementDomConfig navBar;

    private ElementDomConfig brand;

    private ElementDomConfig brandContent;

    private ElementDomConfig brandImgContent;

    private ElementDomConfig brandTextContent;

    private ElementDomConfig body;

    private ElementDomConfig bodyNav;

    private ElementDomConfig bodyNavItem;

    private ElementDomConfig bodyNavItemText;

    private ElementDomConfig bodyNavItemTextActive;

    private ElementDomConfig bodyNavItemChildren;

    private ElementDomConfig bodyRight;

    private ElementDomConfig bodyRightItem;

    private ElementDomConfig bodyRightItemButton;

    public NavBarElementEntity() {
        super(ElementTypeEnum.NAV_BAR);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
