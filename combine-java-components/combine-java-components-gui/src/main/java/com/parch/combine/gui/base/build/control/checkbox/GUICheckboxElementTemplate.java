package com.parch.combine.gui.base.build.control.checkbox;

import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class GUICheckboxElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "checkboxes", name = "多选元素集合样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig checkboxes;

    @Field(key = "checkbox", name = "多选元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig checkbox;

    public ElementConfig getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(ElementConfig checkbox) {
        this.checkbox = checkbox;
    }

    public ElementConfig getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(ElementConfig checkboxes) {
        this.checkboxes = checkboxes;
    }
}
