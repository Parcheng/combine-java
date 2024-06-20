package com.parch.combine.gui.base.control.img;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlImgLogicConfig extends GUIControlLogicConfig {

    @Field(key = "path", name = "图片路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String path();

    @Field(key = "width", name = "图片宽度", type = FieldTypeEnum.NUMBER)
    @FieldDesc("默认以图片的实际宽度为准")
    Integer width();

    @Field(key = "height", name = "图片高度", type = FieldTypeEnum.NUMBER)
    @FieldDesc("默认以图片的实际高度为准")
    Integer height();
}
