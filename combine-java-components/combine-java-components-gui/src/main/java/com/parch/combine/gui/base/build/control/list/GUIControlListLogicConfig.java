package com.parch.combine.gui.base.build.control.list;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlListLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "集合数据集合", type = FieldTypeEnum.ANY, isArray = true)
    Object[] value();

    @Field(key = "orientation", name = "排列方式", type = FieldTypeEnum.SELECT, defaultValue = "VERTICAL")
    @FieldSelect(enumClass = ListOrientationEnum.class)
    String orientation();

    @Field(key = "bodyElementId", name = "集合内容的GUI元素ID", type = FieldTypeEnum.TEXT, isRequired = true)
    String bodyElementId();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
