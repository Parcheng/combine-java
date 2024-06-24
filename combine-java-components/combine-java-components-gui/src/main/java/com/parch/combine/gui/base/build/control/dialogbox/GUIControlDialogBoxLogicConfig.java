package com.parch.combine.gui.base.build.control.dialogbox;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;

public interface GUIControlDialogBoxLogicConfig extends GUIControlLogicConfig {

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    String title();

    @Field(key = "width", name = "宽度", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer width();

    @Field(key = "height", name = "高度", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer height();

    @Field(key = "visible", name = "是否可见", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean visible();

    @Field(key = "elementIds", name = "GUI元素ID集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] elementIds();
}
