package com.parch.combine.gui.base.control.panel;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUIButtonElementTemplate {

    @Field(key = "external", name = "外部元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig external;

    @Field(key = "radio", name = "单选元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig radio;

    public ElementConfig getExternal() {
        return external;
    }

    public void setExternal(ElementConfig external) {
        this.external = external;
    }

    public ElementConfig getRadio() {
        return radio;
    }

    public void setRadio(ElementConfig radio) {
        this.radio = radio;
    }
}
