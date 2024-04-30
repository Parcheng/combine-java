package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.VideoSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "视频页面元素", desc = "当 TYPE = VIDEO 时的参数列表")
public class VideoElementEntity extends ElementEntity<VideoSettings> {

    @ComponentField(key = "video", name = "视频DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig video;

    @ComponentField(key = "mp4", name = "mp4格式视频DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig mp4;

    @ComponentField(key = "ogg", name = "ogg格式视频DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig ogg;

    @ComponentField(key = "webm", name = "webm格式视频DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig webm;

    @ComponentField(key = "content", name = "不兼容展示的内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = VideoSettings.class)
    private VideoSettings settings;

    public VideoElementEntity() {
        super(ElementTypeEnum.VIDEO);
    }

    @Override
    public VideoSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(VideoSettings settings) {
        this.settings = settings;
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
