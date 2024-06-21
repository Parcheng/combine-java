package com.parch.combine.gui.base.control.dialogbox;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;
import com.parch.combine.gui.base.control.slider.SliderOrientationEnum;

public interface GUIControlDialogBoxLogicConfig extends GUIControlLogicConfig {

    @Field(key = "text", name = "按钮显示文本", type = FieldTypeEnum.TEXT, defaultValue = "HORIZONTAL")
    String orientation();
}
