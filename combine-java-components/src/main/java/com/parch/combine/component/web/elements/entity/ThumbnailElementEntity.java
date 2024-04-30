package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.ThumbnailSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class ThumbnailElementEntity extends ElementEntity<ThumbnailSettings> {

    private ElementDomConfig external;

    private ElementDomConfig img;

    private ElementDomConfig content;

    private ElementDomConfig contentTitle;

    private ElementDomConfig contentText;

    private ElementDomConfig contentButton;

    public ThumbnailElementEntity() {
        super(ElementTypeEnum.THUMBNAIL);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
