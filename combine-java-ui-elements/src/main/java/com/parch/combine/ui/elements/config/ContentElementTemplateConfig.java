package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class ContentElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "left", name = "内容左元素DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig left;

    @Field(key = "leftImg", name = "内容左元素中图片DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig leftImg;

    @Field(key = "right", name = "内容右元素DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig right;

    @Field(key = "rightImg", name = "内容右边元素中图片DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig rightImg;

    @Field(key = "body", name = "内容元素DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig body;

    @Field(key = "bodyTitle", name = "内容标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyTitle;

    @Field(key = "bodyContent", name = "内容文本DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyContent;

    @Field(key = "bodyChildren", name = "DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bodyChildren;

    public DomConfig getLeft() {
        return left;
    }

    public void setLeft(DomConfig left) {
        this.left = left;
    }

    public DomConfig getRight() {
        return right;
    }

    public void setRight(DomConfig right) {
        this.right = right;
    }

    public DomConfig getBody() {
        return body;
    }

    public void setBody(DomConfig body) {
        this.body = body;
    }

    public DomConfig getBodyTitle() {
        return bodyTitle;
    }

    public void setBodyTitle(DomConfig bodyTitle) {
        this.bodyTitle = bodyTitle;
    }

    public DomConfig getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(DomConfig bodyContent) {
        this.bodyContent = bodyContent;
    }

    public DomConfig getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(DomConfig leftImg) {
        this.leftImg = leftImg;
    }

    public DomConfig getRightImg() {
        return rightImg;
    }

    public void setRightImg(DomConfig rightImg) {
        this.rightImg = rightImg;
    }

    public DomConfig getBodyChildren() {
        return bodyChildren;
    }

    public void setBodyChildren(DomConfig bodyChildren) {
        this.bodyChildren = bodyChildren;
    }
}
