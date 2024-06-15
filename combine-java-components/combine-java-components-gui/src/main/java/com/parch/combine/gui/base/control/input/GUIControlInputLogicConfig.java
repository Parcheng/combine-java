package com.parch.combine.gui.base.control.input;

import com.parch.combine.gui.base.control.GUIControlLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GUIControlInputLogicConfig extends GUIControlLogicConfig {

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT)
    String text();

    @Field(key = "columns", name = "输入框长度（单位:字符数）", type = FieldTypeEnum.NUMBER, defaultValue = "15")
    Integer columns();
}
