package com.parch.combine.gui.base.operations.visible;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.operations.GUIOperationConfig;

public interface GUIOperationVisibleLogicConfig extends GUIOperationConfig {

    @Field(key = "visibl", name = "是否可见", type = FieldTypeEnum.BOOLEAN, isRequired = true)
    Boolean visible();
}
