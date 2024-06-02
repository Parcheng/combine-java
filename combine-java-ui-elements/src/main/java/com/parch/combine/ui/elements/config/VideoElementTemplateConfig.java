package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class VideoElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "video", name = "视频DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig video;

    @Field(key = "mp4", name = "mp4格式视频DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig mp4;

    @Field(key = "ogg", name = "ogg格式视频DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig ogg;

    @Field(key = "webm", name = "webm格式视频DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig webm;

    @Field(key = "content", name = "不兼容展示的内容DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig content;

    public DomConfig getMp4() {
        return mp4;
    }

    public void setMp4(DomConfig mp4) {
        this.mp4 = mp4;
    }

    public DomConfig getOgg() {
        return ogg;
    }

    public void setOgg(DomConfig ogg) {
        this.ogg = ogg;
    }

    public DomConfig getContent() {
        return content;
    }

    public void setContent(DomConfig content) {
        this.content = content;
    }

    public DomConfig getWebm() {
        return webm;
    }

    public void setWebm(DomConfig webm) {
        this.webm = webm;
    }

    public DomConfig getVideo() {
        return video;
    }

    public void setVideo(DomConfig video) {
        this.video = video;
    }
}
