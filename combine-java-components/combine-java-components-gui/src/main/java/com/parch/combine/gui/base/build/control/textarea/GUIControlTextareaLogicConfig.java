package com.parch.combine.gui.base.build.control.textarea;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlTextareaLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "值", type = FieldTypeEnum.TEXT, isRequired = true)
    String value();

    @Field(key = "columns", name = "宽度（单位字符个数）", type = FieldTypeEnum.NUMBER, defaultValue = "15")
    Integer columns();

    @Field(key = "isWrapStyleWord", name = "是否按单词换行", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean isWrapStyleWord();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
