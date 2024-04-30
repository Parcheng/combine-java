package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.ContentSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "内容页面元素", desc = "当 TYPE = CONTENT 时的参数列表")
public class ContentElementEntity extends ElementEntity<ContentSettings> {

    @ComponentField(key = "left", name = "内容左元素DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig left;

    @ComponentField(key = "leftImg", name = "内容左元素中图片DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig leftImg;

    @ComponentField(key = "right", name = "内容右元素DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig right;

    @ComponentField(key = "rightImg", name = "内容右边元素中图片DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig rightImg;

    @ComponentField(key = "body", name = "内容元素DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig body;

    @ComponentField(key = "bodyTitle", name = "内容标题DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyTitle;

    @ComponentField(key = "bodyContent", name = "内容文本DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyContent;

    @ComponentField(key = "bodyChildren", name = "DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bodyChildren;

    @ComponentField(key = "settings", name = "子内容外部DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = ContentSettings.class)
    private ContentSettings settings;

    public ContentElementEntity() {
        super(ElementTypeEnum.CONTENT);
    }

    @Override
    public ContentSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(ContentSettings settings) {
        this.settings = settings;
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
