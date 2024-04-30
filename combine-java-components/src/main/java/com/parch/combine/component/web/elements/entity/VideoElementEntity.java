package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.VideoSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class VideoElementEntity extends ElementEntity<VideoSettings> {

    private ElementDomConfig external;

    private ElementDomConfig video;

    private ElementDomConfig mp4;

    private ElementDomConfig ogg;

    private ElementDomConfig webm;

    private ElementDomConfig content;

    public VideoElementEntity() {
        super(ElementTypeEnum.VIDEO);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getMp4() {
        return mp4;
    }

    public void setMp4(ElementDomConfig mp4) {
        this.mp4 = mp4;
    }

    public ElementDomConfig getOgg() {
        return ogg;
    }

    public void setOgg(ElementDomConfig ogg) {
        this.ogg = ogg;
    }

    public ElementDomConfig getContent() {
        return content;
    }

    public void setContent(ElementDomConfig content) {
        this.content = content;
    }

    public ElementDomConfig getWebm() {
        return webm;
    }

    public void setWebm(ElementDomConfig webm) {
        this.webm = webm;
    }

    public ElementDomConfig getVideo() {
        return video;
    }

    public void setVideo(ElementDomConfig video) {
        this.video = video;
    }
}
