package com.parch.combine.gui.base.build.control.radio;

import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlRadioLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "值", type = FieldTypeEnum.TEXT)
    String value();

    @Field(key = "options", name = "选项集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @FieldObject(GUIControlOptionConfig.class)
    GUIControlOptionConfig[] options();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
