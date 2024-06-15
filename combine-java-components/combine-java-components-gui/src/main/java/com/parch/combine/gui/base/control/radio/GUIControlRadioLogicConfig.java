package com.parch.combine.gui.base.control.radio;

import com.parch.combine.gui.base.control.GUIControlLogicConfig;
import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GUIControlRadioLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "值", type = FieldTypeEnum.TEXT)
    String value();

    @Field(key = "options", name = "选项集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @FieldObject(GUIControlOptionConfig.class)
    GUIControlOptionConfig[] options();
}
