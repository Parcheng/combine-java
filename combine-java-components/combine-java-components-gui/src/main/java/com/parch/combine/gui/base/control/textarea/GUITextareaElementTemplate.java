package com.parch.combine.gui.base.control.textarea;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUITextareaElementTemplate {

    @Field(key = "external", name = "外部元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig external;

    @Field(key = "textArea", name = "多行文本输入框元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig textArea;

    public ElementConfig getExternal() {
        return external;
    }

    public void setExternal(ElementConfig external) {
        this.external = external;
    }

    public ElementConfig getTextArea() {
        return textArea;
    }

    public void setTextArea(ElementConfig textArea) {
        this.textArea = textArea;
    }
}
