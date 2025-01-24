package com.parch.combine.gui.base.operations.value.set;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.operations.GUIOperationConfig;

public interface GUIOperationValueSetLogicConfig extends GUIOperationConfig {

    @Field(key = "value", name = "值", type = FieldTypeEnum.ANY, isRequired = true)
    Object value();
}
