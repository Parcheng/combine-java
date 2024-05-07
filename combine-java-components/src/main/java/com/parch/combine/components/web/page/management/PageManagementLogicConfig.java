package com.parch.combine.components.web.page.management;

import com.parch.combine.components.web.page.WebPageLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class PageManagementLogicConfig extends WebPageLogicConfig {

    @Field(key = "header", name = "页面头部元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement header;

    @Field(key = "footer", name = "页面底部元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement footer;

    @Field(key = "left", name = "页面左侧元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement left;

    @Field(key = "right", name = "页面右侧元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement right;

    @Field(key = "content", name = "页面内容（中间）元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
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
