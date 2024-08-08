package com.parch.combine.gui.base.build.control.file;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUIFileInputElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "input", name = "输入元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig input;

    @Field(key = "choose", name = "选择按钮样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig choose;

    public ElementConfig getInput() {
        return input;
    }

    public void setInput(ElementConfig input) {
        this.input = input;
    }

    public ElementConfig getChoose() {
        return choose;
    }

    public void setChoose(ElementConfig choose) {
        this.choose = choose;
    }
}
