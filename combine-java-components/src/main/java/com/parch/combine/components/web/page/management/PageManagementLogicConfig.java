package com.parch.combine.components.web.page.management;

import com.parch.combine.components.web.page.WebPageLogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class PageManagementLogicConfig extends WebPageLogicConfig {

    @ComponentField(key = "header", name = "页面头部元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = HtmlElement.class)
    private HtmlElement header;

    @ComponentField(key = "footer", name = "页面底部元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = HtmlElement.class)
    private HtmlElement footer;

    @ComponentField(key = "left", name = "页面左侧元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = HtmlElement.class)
    private HtmlElement left;

    @ComponentField(key = "right", name = "页面右侧元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = HtmlElement.class)
    private HtmlElement right;

    @ComponentField(key = "content", name = "页面内容（中间）元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = HtmlElement.class)
    private HtmlElement content;

    public HtmlElement getHeader() {
        return header;
    }

    public void setHeader(HtmlElement header) {
        this.header = header;
    }

    public HtmlElement getFooter() {
        return footer;
    }

    public void setFooter(HtmlElement footer) {
        this.footer = footer;
    }

    public HtmlElement getLeft() {
        return left;
    }

    public void setLeft(HtmlElement left) {
        this.left = left;
    }

    public HtmlElement getRight() {
        return right;
    }

    public void setRight(HtmlElement right) {
        this.right = right;
    }

    public HtmlElement getContent() {
        return content;
    }

    public void setContent(HtmlElement content) {
        this.content = content;
    }
}
