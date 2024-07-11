package com.parch.combine.gui.base.operations;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface GUIOperationConfig extends ILogicConfig {

    @Field(key = "domain", name = "域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    String domain();

    @Field(key = "elementId", name = "GUI元素ID", type = FieldTypeEnum.TEXT, isRequired = true)
    String elementId();

//    @Field(key = "hasRegex", name = "开启正则匹配元素ID", type = FieldTypeEnum.TEXT, defaultValue = "false")
//    Boolean hasRegex();
}
