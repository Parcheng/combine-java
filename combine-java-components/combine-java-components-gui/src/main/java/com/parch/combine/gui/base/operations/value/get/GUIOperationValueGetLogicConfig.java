package com.parch.combine.gui.base.operations.value.get;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.operations.GUIOperationConfig;

public interface GUIOperationValueGetLogicConfig extends GUIOperationConfig {

    @Field(key = "value", name = "值", type = FieldTypeEnum.ANY, isRequired = true)
    Object value();
}
