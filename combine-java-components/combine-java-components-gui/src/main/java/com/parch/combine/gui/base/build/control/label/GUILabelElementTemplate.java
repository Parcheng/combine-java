package com.parch.combine.gui.base.build.control.label;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUILabelElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "label", name = "文本样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig label;

    public ElementConfig getLabel() {
        return label;
    }

    public void setLabel(ElementConfig label) {
        this.label = label;
    }
}
