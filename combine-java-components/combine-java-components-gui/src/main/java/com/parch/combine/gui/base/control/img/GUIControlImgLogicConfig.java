package com.parch.combine.gui.base.control.img;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlImgLogicConfig extends GUIControlLogicConfig {

    @Field(key = "path", name = "图片路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String path();

    @Field(key = "x", name = "X轴起始位置", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    Integer x();

    @Field(key = "y", name = "Y轴起始位置", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    Integer y();
}
