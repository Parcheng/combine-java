package com.parch.combine.core.ui.base.page;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.DomConfig;

import java.util.List;

public class PageConfig {

    @Field(key = "id", name = "元素ID", type = FieldTypeEnum.TEXT, defaultValue = "随机字符粗")
    private String id;

    private List<String> elementIds;

    @Field(key = "header", name = "页面头部元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = WebPageLogicConfig.HtmlElement.class)
    private WebPageLogicConfig.HtmlElement header;

    @Field(key = "footer", name = "页面底部元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = WebPageLogicConfig.HtmlElement.class)
    private WebPageLogicConfig.HtmlElement footer;

    @Field(key = "left", name = "页面左侧元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = WebPageLogicConfig.HtmlElement.class)
    private WebPageLogicConfig.HtmlElement left;

    @Field(key = "right", name = "页面右侧元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = WebPageLogicConfig.HtmlElement.class)
    private WebPageLogicConfig.HtmlElement right;

    @Field(key = "content", name = "页面内容（中间）元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = WebPageLogicConfig.HtmlElement.class)
    private WebPageLogicConfig.HtmlElement content;

    public List<String> getElementIds() {
        return elementIds;
    }

    public void setElementIds(List<String> elementIds) {
        this.elementIds = elementIds;
    }

    public WebPageLogicConfig.HtmlElement getHeader() {
        return header;
    }

    public void setHeader(WebPageLogicConfig.HtmlElement header) {
        this.header = header;
    }

    public WebPageLogicConfig.HtmlElement getFooter() {
        return footer;
    }

    public void setFooter(WebPageLogicConfig.HtmlElement footer) {
        this.footer = footer;
    }

    public WebPageLogicConfig.HtmlElement getLeft() {
        return left;
    }

    public void setLeft(WebPageLogicConfig.HtmlElement left) {
        this.left = left;
    }

    public WebPageLogicConfig.HtmlElement getRight() {
        return right;
    }

    public void setRight(WebPageLogicConfig.HtmlElement right) {
        this.right = right;
    }

    public WebPageLogicConfig.HtmlElement getContent() {
        return content;
    }

    public void setContent(WebPageLogicConfig.HtmlElement content) {
        this.content = content;
    }
}
