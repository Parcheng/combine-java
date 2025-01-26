package com.parch.combine.gui.base.build.control.button;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldMapValue;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;

import java.util.Map;

public class GUIButtonElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "button", name = "按钮样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig button;

    @Field(key = "buttonTypes", name = "按钮类型样式配置", type = FieldTypeEnum.MAP)
    @FieldMapValue(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private Map<String, ElementConfig> buttonTypes;

    public ElementConfig getButton() {
        return button;
    }

    public void setButton(ElementConfig button) {
        this.button = button;
    }

    public Map<String, ElementConfig> getButtonTypes() {
        return buttonTypes;
    }

    public void setButtonTypes(Map<String, ElementConfig> buttonTypes) {
        this.buttonTypes = buttonTypes;
    }
}
