package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.VideoSettings;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "视频页面元素", desc = "当 TYPE = VIDEO 时的参数列表")
public class VideoElementEntity extends ElementEntity<VideoSettings> {

    @Field(key = "video", name = "视频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig video;

    @Field(key = "mp4", name = "mp4格式视频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig mp4;

    @Field(key = "ogg", name = "ogg格式视频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig ogg;

    @Field(key = "webm", name = "webm格式视频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig webm;

    @Field(key = "content", name = "不兼容展示的内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = VideoSettings.class)
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
