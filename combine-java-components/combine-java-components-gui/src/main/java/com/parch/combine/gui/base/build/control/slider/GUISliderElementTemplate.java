package com.parch.combine.gui.base.build.control.slider;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;

public class GUISliderElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "slider", name = "滑块样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig slider;

    public ElementConfig getSlider() {
        return slider;
    }

    public void setSlider(ElementConfig slider) {
        this.slider = slider;
    }
}
