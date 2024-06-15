package com.parch.combine.gui.base.control.panel;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;
import com.parch.combine.gui.base.control.GUIControlOptionConfig;

public interface GUIControlPanelLogicConfig extends GUIControlLogicConfig {

    @Field(key = "values", name = "值集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] values();

    @Field(key = "options", name = "选项集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @FieldObject(GUIControlOptionConfig.class)
    GUIControlOptionConfig[] options();
}
