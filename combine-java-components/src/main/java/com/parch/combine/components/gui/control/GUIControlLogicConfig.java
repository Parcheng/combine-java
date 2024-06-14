package com.parch.combine.components.gui.control;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface GUIControlLogicConfig extends ILogicConfig {

    @Field(key = "elementId", name = "元素ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("用于动态控制该控件，或获取数据等操作，默认为组件ID")
    String elementId();
}
