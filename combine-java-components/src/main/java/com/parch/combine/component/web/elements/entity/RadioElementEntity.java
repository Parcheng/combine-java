package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.RadioSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class RadioElementEntity extends ElementEntity<RadioSettings> {

    private ElementDomConfig external;

    private ElementDomConfig inline;

    private ElementDomConfig multiline;
    private ElementDomConfig disabled;
    private ElementDomConfig option;

    public RadioElementEntity() {
        super(ElementTypeEnum.RADIO);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getInline() {
        return inline;
    }

    public void setInline(ElementDomConfig inline) {
        this.inline = inline;
    }

    public ElementDomConfig getMultiline() {
        return multiline;
    }

    public void setMultiline(ElementDomConfig multiline) {
        this.multiline = multiline;
    }

    public ElementDomConfig getDisabled() {
        return disabled;
    }

    public void setDisabled(ElementDomConfig disabled) {
        this.disabled = disabled;
    }

    public ElementDomConfig getOption() {
        return option;
    }

    public void setOption(ElementDomConfig option) {
        this.option = option;
    }
}
