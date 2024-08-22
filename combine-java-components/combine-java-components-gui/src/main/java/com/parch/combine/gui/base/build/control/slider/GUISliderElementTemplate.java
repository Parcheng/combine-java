package com.parch.combine.gui.base.build.control.slider;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

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
