package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.InputSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class InputElementEntity extends ElementEntity<InputSettings> {

    private ElementDomConfig external;

    private ElementDomConfig addon;

    private ElementDomConfig input;

    public InputElementEntity() {
        super(ElementTypeEnum.INPUT);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getAddon() {
        return addon;
    }

    public void setAddon(ElementDomConfig addon) {
        this.addon = addon;
    }

    public ElementDomConfig getInput() {
        return input;
    }

    public void setInput(ElementDomConfig input) {
        this.input = input;
    }
}
