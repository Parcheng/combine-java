package com.parch.combine.gui.base.build.control.checkbox;

import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlCheckboxLogicConfig extends GUIControlLogicConfig {

    @Field(key = "values", name = "值集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] values();

    @Field(key = "options", name = "选项集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @FieldObject(GUIControlOptionConfig.class)
    GUIControlOptionConfig[] options();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
