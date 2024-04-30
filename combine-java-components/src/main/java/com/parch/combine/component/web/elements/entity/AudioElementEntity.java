package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.settings.AudioSettings;
import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class AudioElementEntity extends ElementEntity<AudioSettings> {

    private ElementDomConfig external;

    private ElementDomConfig audio;

    private ElementDomConfig mp3;

    private ElementDomConfig ogg;

    private ElementDomConfig content;

    public AudioElementEntity() {
        super(ElementTypeEnum.AUDIO);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
}
