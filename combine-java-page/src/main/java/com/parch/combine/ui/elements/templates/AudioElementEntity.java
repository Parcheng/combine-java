package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.AudioSettings;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "音频页面元素", desc = "当 TYPE = AUDIO 时的参数列表")
public class AudioElementEntity extends ElementEntity<AudioSettings> {

    @Field(key = "audio", name = "音频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig audio;

    @Field(key = "mp3", name = "mp3格式音频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig mp3;

    @Field(key = "ogg", name = "ogg格式音频DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig ogg;

    @Field(key = "content", name = "不兼容展示的内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = AudioSettings.class)
    private AudioSettings settings;

    public AudioElementEntity() {
        super(ElementTypeEnum.AUDIO);
    }

    public ElementDomConfig getMp3() {
        return mp3;
    }

    public void setMp3(ElementDomConfig mp3) {
        this.mp3 = mp3;
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

    public ElementDomConfig getAudio() {
        return audio;
    }

    public void setAudio(ElementDomConfig audio) {
        this.audio = audio;
    }

    @Override
    public AudioSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(AudioSettings settings) {
        this.settings = settings;
    }
}
