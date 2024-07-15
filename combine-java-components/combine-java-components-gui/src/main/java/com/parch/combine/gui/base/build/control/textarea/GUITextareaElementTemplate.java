package com.parch.combine.gui.base.build.control.textarea;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUITextareaElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "textarea", name = "多行文本输入框元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig textarea;

    public ElementConfig getTextarea() {
        return textarea;
    }

    public void setTextarea(ElementConfig textarea) {
        this.textarea = textarea;
    }
}
