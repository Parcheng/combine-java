package com.parch.combine.component.web.page.management;

import com.parch.combine.component.web.page.WebLogicConfig;

/**
 * 逻辑配置类
 */
public class PageManagementLogicConfig extends WebLogicConfig {

    private HtmlElement header;

    private HtmlElement footer;

    private HtmlElement left;

    private HtmlElement right;

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
