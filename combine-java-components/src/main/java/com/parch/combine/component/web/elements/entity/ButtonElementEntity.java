package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.ButtonSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class ButtonElementEntity extends ElementEntity<ButtonSettings> {

    /**
     * 按钮集合
     */
    private ElementDomConfig button;

    public ButtonElementEntity() {
        super(ElementTypeEnum.BUTTON);
    }

    public ElementDomConfig getButton() {
        return button;
    }

    public void setButton(ElementDomConfig button) {
        this.button = button;
    }
}