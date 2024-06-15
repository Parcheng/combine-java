package com.parch.combine.gui.base.control.textarea;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlTextareaLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "值", type = FieldTypeEnum.TEXT, isRequired = true)
    String value();

    @Field(key = "columns", name = "宽度（单位字符个数）", type = FieldTypeEnum.NUMBER, defaultValue = "15")
    Integer columns();

    @Field(key = "isWrapStyleWord", name = "是否按单词换行", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean isWrapStyleWord();
}
