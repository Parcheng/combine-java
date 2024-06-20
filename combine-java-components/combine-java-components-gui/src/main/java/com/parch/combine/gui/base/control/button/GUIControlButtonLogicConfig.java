package com.parch.combine.gui.base.control.button;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlButtonLogicConfig extends GUIControlLogicConfig {

    @Field(key = "text", name = "按钮显示文本", type = FieldTypeEnum.TEXT)
    String text();
}
