package com.parch.combine.gui.base.build.control.input;

import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlInputLogicConfig extends GUIControlLogicConfig {

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT)
    String text();

    @Field(key = "columns", name = "输入框长度（单位:字符数）", type = FieldTypeEnum.NUMBER, defaultValue = "15")
    Integer columns();

    @Field(key = "prefix", name = "前缀", type = FieldTypeEnum.TEXT)
    String prefix();

    @Field(key = "suffix", name = "后缀", type = FieldTypeEnum.TEXT)
    String suffix();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
