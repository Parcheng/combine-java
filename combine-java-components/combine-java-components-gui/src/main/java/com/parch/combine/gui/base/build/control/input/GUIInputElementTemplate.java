package com.parch.combine.gui.base.build.control.input;

import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class GUIInputElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "input", name = "输入元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig input;

    @Field(key = "symbol", name = "前后缀元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig symbol;

    public ElementConfig getInput() {
        return input;
    }

    public void setInput(ElementConfig input) {
        this.input = input;
    }

    public ElementConfig getSymbol() {
        return symbol;
    }

    public void setSymbol(ElementConfig symbol) {
        this.symbol = symbol;
    }
}
