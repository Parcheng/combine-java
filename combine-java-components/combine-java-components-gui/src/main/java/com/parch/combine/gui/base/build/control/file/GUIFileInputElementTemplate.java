package com.parch.combine.gui.base.build.control.file;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;

public class GUIFileInputElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "input", name = "输入元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig input;

    @Field(key = "choose", name = "选择按钮样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
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
