package com.parch.combine.gui.base.control.label;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlLabelLogicConfig extends GUIControlLogicConfig {

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    String text();
}
