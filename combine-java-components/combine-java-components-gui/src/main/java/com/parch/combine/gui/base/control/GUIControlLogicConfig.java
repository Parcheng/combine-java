package com.parch.combine.gui.base.control;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

import java.util.Map;

public interface GUIControlLogicConfig extends ILogicConfig {

    @Field(key = "elementId", name = "元素ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("用于动态控制该控件，或获取数据等操作，默认为组件ID")
    String elementId();

    @Field(key = "params", name = "元素内部参数", type = FieldTypeEnum.MAP)
    @FieldDesc("可用于元素内部的一些触发事件，调用函数等")
    Map<String, Object> params();
}
