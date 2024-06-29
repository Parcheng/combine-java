package com.parch.combine.gui.base.operations.call;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.operations.GUIOperationConfig;

public interface GUIOperationCallLogicConfig extends GUIOperationConfig {

    @Field(key = "key", name = "函数KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();

    @Field(key = "params", name = "函数参数", type = FieldTypeEnum.ANY, isArray = true)
    Object[] params();
}
