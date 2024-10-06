package com.parch.combine.html.base.element.audio;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class AudioElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "audio", name = "音频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig audio;

    @Field(key = "mp3", name = "mp3格式音频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig mp3;

    @Field(key = "ogg", name = "ogg格式音频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig ogg;

    @Field(key = "content", name = "不兼容展示的内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig content;

    public DomConfig getAudio() {
        return audio;
    }

    public void setAudio(DomConfig audio) {
        this.audio = audio;
    }

    public DomConfig getMp3() {
        return mp3;
    }

    public void setMp3(DomConfig mp3) {
        this.mp3 = mp3;
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
}
