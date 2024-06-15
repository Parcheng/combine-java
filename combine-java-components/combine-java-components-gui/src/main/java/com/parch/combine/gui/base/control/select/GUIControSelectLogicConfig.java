package com.parch.combine.gui.base.control.select;

import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GUIControSelectLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "当前值", type = FieldTypeEnum.TEXT)
    String value();

    @Field(key = "options", name = "下拉选项集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @FieldObject(GUIControlOptionConfig.class)
    GUIControlOptionConfig[] options();
}
