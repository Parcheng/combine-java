package com.parch.combine.tool.base.event.push;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

import java.util.Map;

public interface ToolEventPushLogicConfig extends ILogicConfig {

    @Field(key = "eventKey", name = "要监听的事件KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String eventKey();

    @Field(key = "data", name = "要推送的数据", type = FieldTypeEnum.MAP, isRequired = true)
    @FieldDesc("注意：该数据必须为对象结构（键值对）")
    Map<String, Object> data();
}
