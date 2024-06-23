package com.parch.combine.gui.base.build.control.dialogbox;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;

public interface GUIControlDialogBoxLogicConfig extends GUIControlLogicConfig {

    @Field(key = "text", name = "按钮显示文本", type = FieldTypeEnum.TEXT, defaultValue = "HORIZONTAL")
    String orientation();
}
