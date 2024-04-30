package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.ThumbnailSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "缩略图页面元素", desc = "当 TYPE = THUMBNAIL 时的参数列表")
public class ThumbnailElementEntity extends ElementEntity<ThumbnailSettings> {

    @ComponentField(key = "img", name = "图片DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig img;

    @ComponentField(key = "content", name = "内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @ComponentField(key = "contentTitle", name = "内容标题DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig contentTitle;

    @ComponentField(key = "contentText", name = "内容段落行DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig contentText;

    @ComponentField(key = "contentButton", name = "内容操作按钮栏DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig contentButton;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = ThumbnailSettings.class)
    private ThumbnailSettings settings;

    public ThumbnailElementEntity() {
        super(ElementTypeEnum.THUMBNAIL);
    }

    @Override
    public ThumbnailSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(ThumbnailSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getImg() {
        return img;
    }

    public void setImg(ElementDomConfig img) {
        this.img = img;
    }

    public ElementDomConfig getContent() {
        return content;
    }

    public void setContent(ElementDomConfig content) {
        this.content = content;
    }

    public ElementDomConfig getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(ElementDomConfig contentTitle) {
        this.contentTitle = contentTitle;
    }

    public ElementDomConfig getContentText() {
        return contentText;
    }

    public void setContentText(ElementDomConfig contentText) {
        this.contentText = contentText;
    }

    public ElementDomConfig getContentButton() {
        return contentButton;
    }

    public void setContentButton(ElementDomConfig contentButton) {
        this.contentButton = contentButton;
    }
}
