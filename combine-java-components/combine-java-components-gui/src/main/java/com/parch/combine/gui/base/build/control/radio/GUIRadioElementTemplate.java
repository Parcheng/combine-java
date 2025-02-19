package com.parch.combine.gui.base.build.control.radio;

import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public class GUIRadioElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "radios", name = "单选元素集合样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig radios;

    @Field(key = "radio", name = "单选元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig radio;

    public ElementConfig getRadios() {
        return radios;
    }

    public void setRadios(ElementConfig radios) {
        this.radios = radios;
    }

    public ElementConfig getRadio() {
        return radio;
    }

    public void setRadio(ElementConfig radio) {
        this.radio = radio;
    }
}
