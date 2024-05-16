package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class ThumbnailElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "img", name = "图片DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig img;

    @Field(key = "content", name = "内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig content;

    @Field(key = "contentTitle", name = "内容标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig contentTitle;

    @Field(key = "contentText", name = "内容段落行DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig contentText;

    @Field(key = "contentButton", name = "内容操作按钮栏DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig contentButton;

    public DomConfig getImg() {
        return img;
    }

    public void setImg(DomConfig img) {
        this.img = img;
    }

    public DomConfig getContent() {
        return content;
    }

    public void setContent(DomConfig content) {
        this.content = content;
    }

    public DomConfig getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(DomConfig contentTitle) {
        this.contentTitle = contentTitle;
    }

    public DomConfig getContentText() {
        return contentText;
    }

    public void setContentText(DomConfig contentText) {
        this.contentText = contentText;
    }

    public DomConfig getContentButton() {
        return contentButton;
    }

    public void setContentButton(DomConfig contentButton) {
        this.contentButton = contentButton;
    }
}
