package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.ContentSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class ContentElementEntity extends ElementEntity<ContentSettings> {

    private ElementDomConfig external;

    private ElementDomConfig left;

    private ElementDomConfig leftImg;

    private ElementDomConfig right;

    private ElementDomConfig rightImg;

    private ElementDomConfig body;

    private ElementDomConfig bodyTitle;

    private ElementDomConfig bodyContent;

    private ElementDomConfig bodyChildren;

    public ContentElementEntity() {
        super(ElementTypeEnum.CONTENT);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getLeft() {
        return left;
    }

    public void setLeft(ElementDomConfig left) {
        this.left = left;
    }

    public ElementDomConfig getRight() {
        return right;
    }

    public void setRight(ElementDomConfig right) {
        this.right = right;
    }

    public ElementDomConfig getBody() {
        return body;
    }

    public void setBody(ElementDomConfig body) {
        this.body = body;
    }

    public ElementDomConfig getBodyTitle() {
        return bodyTitle;
    }

    public void setBodyTitle(ElementDomConfig bodyTitle) {
        this.bodyTitle = bodyTitle;
    }

    public ElementDomConfig getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(ElementDomConfig bodyContent) {
        this.bodyContent = bodyContent;
    }

    public ElementDomConfig getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(ElementDomConfig leftImg) {
        this.leftImg = leftImg;
    }

    public ElementDomConfig getRightImg() {
        return rightImg;
    }

    public void setRightImg(ElementDomConfig rightImg) {
        this.rightImg = rightImg;
    }

    public ElementDomConfig getBodyChildren() {
        return bodyChildren;
    }

    public void setBodyChildren(ElementDomConfig bodyChildren) {
        this.bodyChildren = bodyChildren;
    }
}
