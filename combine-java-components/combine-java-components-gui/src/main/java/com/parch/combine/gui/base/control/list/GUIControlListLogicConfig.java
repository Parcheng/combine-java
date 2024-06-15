package com.parch.combine.gui.base.control.list;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;
import com.parch.combine.gui.base.control.GUIControlOptionConfig;

public interface GUIControlListLogicConfig extends GUIControlLogicConfig {

    @Field(key = "data", name = "数据集合", type = FieldTypeEnum.ANY, isArray = true)
    Object[] data();

    @Field(key = "bodyElementId", name = "集合内容的GUI元素ID", type = FieldTypeEnum.TEXT, isRequired = true)
    String bodyElementId();
}
