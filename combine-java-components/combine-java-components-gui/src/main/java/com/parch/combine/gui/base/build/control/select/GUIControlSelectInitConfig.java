package com.parch.combine.gui.base.build.control.select;

import com.parch.combine.gui.base.build.GUIControlInitConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface GUIControlSelectInitConfig extends GUIControlInitConfig {

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUISelectElementTemplate.class)
    GUISelectElementTemplate template();
}
