package com.parch.combine.gui.base.control.html;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlHtmlLogicConfig extends GUIControlLogicConfig {

    @Field(key = "path", name = "路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String path();

    @Field(key = "errorText", name = "加载失败显示文本", type = FieldTypeEnum.TEXT, defaultValue = "Load Error")
    String errorText();
}
