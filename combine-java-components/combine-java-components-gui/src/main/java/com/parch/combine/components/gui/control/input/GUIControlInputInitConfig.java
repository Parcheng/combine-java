package com.parch.combine.components.gui.control.input;

import com.parch.combine.components.gui.control.GUIControlInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GUIControlInputInitConfig extends GUIControlInitConfig {

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUIInputElementTemplate.class)
    GUIInputElementTemplate template();
}
