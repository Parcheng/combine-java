package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.ThumbnailSettings;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "缩略图页面元素", desc = "当 TYPE = THUMBNAIL 时的参数列表")
public class ThumbnailElementEntity extends ElementEntity<ThumbnailSettings> {

    @Field(key = "img", name = "图片DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig img;

    @Field(key = "content", name = "内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @Field(key = "contentTitle", name = "内容标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig contentTitle;

    @Field(key = "contentText", name = "内容段落行DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig contentText;

    @Field(key = "contentButton", name = "内容操作按钮栏DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig contentButton;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = ThumbnailSettings.class)
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
