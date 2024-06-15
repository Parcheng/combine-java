package com.parch.combine.gui.base.control.checkbox;

import com.parch.combine.gui.base.control.GUIControlInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GUIControlCheckboxInitConfig extends GUIControlInitConfig {

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUICheckboxElementTemplate.class)
    GUICheckboxElementTemplate template();
}
