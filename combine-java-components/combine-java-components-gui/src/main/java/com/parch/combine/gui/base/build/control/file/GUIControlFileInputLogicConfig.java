package com.parch.combine.gui.base.build.control.file;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlFileInputLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "默认值", type = FieldTypeEnum.TEXT)
    String value();

    @Field(key = "columns", name = "输入框长度（单位:字符数）", type = FieldTypeEnum.NUMBER, defaultValue = "15")
    Integer columns();

    @Field(key = "chooseText", name = "选择按钮显示文本", type = FieldTypeEnum.TEXT, defaultValue = "...")
    String chooseText();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
