package com.parch.combine.components.gui.control.input;

import com.parch.combine.components.gui.core.style.ElementConfig;
import com.parch.combine.components.gui.core.style.ElementObjectCanstant;
import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class GUIInputElementTemplate {

    @Field(key = "external", name = "外部元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectCanstant.GUI_ELEMENT)
    private ElementConfig external;

    @Field(key = "input", name = "输入元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectCanstant.GUI_ELEMENT)
    private ElementConfig input;

    public ElementConfig getExternal() {
        return external;
    }

    public void setExternal(ElementConfig external) {
        this.external = external;
    }

    public ElementConfig getInput() {
        return input;
    }

    public void setInput(ElementConfig input) {
        this.input = input;
    }
}
