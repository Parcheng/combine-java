package com.parch.combine.gui.base.build.control.tree;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlInitConfig;

public interface GUIControlTreeInitConfig extends GUIControlInitConfig {

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUITreeElementTemplate.class)
    GUITreeElementTemplate template();
}
