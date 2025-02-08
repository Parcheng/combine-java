package com.parch.combine.gui.base.operations.visible;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.operations.GUIOperationConfig;

public interface GUIOperationVisibleLogicConfig extends GUIOperationConfig {

    @Field(key = "visible", name = "是否可见", type = FieldTypeEnum.BOOLEAN, isRequired = true)
    Boolean visible();
}
