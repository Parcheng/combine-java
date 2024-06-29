package com.parch.combine.gui.base.build;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

import java.util.Map;

public interface GUIControlLogicConfig extends ILogicConfig {

    @Field(key = "domain", name = "域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    String domain();

    @Field(key = "elementId", name = "元素ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("用于动态控制该控件，或获取数据等操作，默认为组件ID")
    String elementId();

    @Field(key = "visible", name = "默认是否显示", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean visible();

    @Field(key = "visible", name = "是否替换", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @FieldDesc("存在与该 elementId 相同的元素时，为 true 时该元素将替换掉原元素，否作不替换")
    Boolean replace();

    @Field(key = "data", name = "元素数据", type = FieldTypeEnum.MAP)
    @FieldDesc("用于元素内部使用，如：调用函数等")
    Map<String, Object> data();
}
