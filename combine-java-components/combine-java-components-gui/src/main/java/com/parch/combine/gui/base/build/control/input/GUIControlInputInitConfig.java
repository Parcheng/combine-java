package com.parch.combine.gui.base.build.control.input;

import com.parch.combine.gui.base.build.GUIControlInitConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface GUIControlInputInitConfig extends GUIControlInitConfig {

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUIInputElementTemplate.class)
    GUIInputElementTemplate template();
}
