package com.parch.combine.gui.base.build.control.radio;

import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class GUIRadioElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "radio", name = "单选元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig radio;

    public ElementConfig getRadio() {
        return radio;
    }

    public void setRadio(ElementConfig radio) {
        this.radio = radio;
    }
}
