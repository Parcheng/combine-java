package com.parch.combine.gui.base.build.control.buttons;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlInitConfig;

public interface GUIControlButtonGroupInitConfig extends GUIControlInitConfig {

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUIButtonGroupElementTemplate.class)
    GUIButtonGroupElementTemplate template();
}
