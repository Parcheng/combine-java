package com.parch.combine.gui.base.build.control.buttons;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldMapValue;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

import java.util.Map;

public class GUIButtonGroupElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "external", name = "外部元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig external;

    @Field(key = "button", name = "按钮样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig button;

    @Field(key = "buttonTypes", name = "按钮类型样式配置", type = FieldTypeEnum.MAP)
    @FieldMapValue(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private Map<String, ElementConfig> buttonTypes;

    public ElementConfig getExternal() {
        return external;
    }

    public void setExternal(ElementConfig external) {
        this.external = external;
    }

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
