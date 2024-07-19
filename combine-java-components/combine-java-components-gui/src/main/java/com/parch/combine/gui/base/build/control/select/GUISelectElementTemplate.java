package com.parch.combine.gui.base.build.control.select;

import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class GUISelectElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "select", name = "下拉元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig select;

    @Field(key = "option", name = "下拉项样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig option;

    public ElementConfig getSelect() {
        return select;
    }

    public void setSelect(ElementConfig select) {
        this.select = select;
    }

    public ElementConfig getOption() {
        return option;
    }

    public void setOption(ElementConfig option) {
        this.option = option;
    }
}
