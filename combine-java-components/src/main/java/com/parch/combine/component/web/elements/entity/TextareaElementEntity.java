package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.settings.TextareaSettings;
import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class TextareaElementEntity extends ElementEntity<TextareaSettings> {

    private ElementDomConfig external;

    private ElementDomConfig textarea;

    public TextareaElementEntity() {
        super(ElementTypeEnum.TEXTAREA);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getTextarea() {
        return textarea;
    }

    public void setTextarea(ElementDomConfig textarea) {
        this.textarea = textarea;
    }
}
