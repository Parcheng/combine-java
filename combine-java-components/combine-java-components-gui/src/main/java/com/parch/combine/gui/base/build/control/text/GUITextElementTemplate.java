package com.parch.combine.gui.base.build.control.text;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;

public class GUITextElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "text", name = "文本样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig text;

    public ElementConfig getText() {
        return text;
    }

    public void setText(ElementConfig text) {
        this.text = text;
    }
}
