package com.parch.combine.gui.base.build.control.from;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUIFromElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "item", name = "FROM项样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig item;

    @Field(key = "label", name = "左侧文本样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig label;

    @Field(key = "control", name = "右侧控件样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig control;

    public ElementConfig getItem() {
        return item;
    }

    public void setItem(ElementConfig item) {
        this.item = item;
    }

    public ElementConfig getLabel() {
        return label;
    }

    public void setLabel(ElementConfig label) {
        this.label = label;
    }

    public ElementConfig getControl() {
        return control;
    }

    public void setControl(ElementConfig control) {
        this.control = control;
    }
}
