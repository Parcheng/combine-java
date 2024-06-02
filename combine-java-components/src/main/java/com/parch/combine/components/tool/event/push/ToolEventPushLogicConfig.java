package com.parch.combine.components.tool.event.push;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.old.LogicConfig;

import java.util.Map;

public class ToolEventPushLogicConfig extends ILogicConfig {

    @Field(key = "eventKey", name = "要监听的事件KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String eventKey;

    @Field(key = "data", name = "要推送的数据", type = FieldTypeEnum.ANY, isRequired = true)
    @FieldDesc("注意：该数据必须为对象结构（键值对）")
    private Map<String, Object> data;

    @Override
    public void init() {
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
