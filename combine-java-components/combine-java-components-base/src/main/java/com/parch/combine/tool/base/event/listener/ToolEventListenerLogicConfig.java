package com.parch.combine.tool.base.event.listener;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ToolEventListenerLogicConfig extends ILogicConfig {

    @Field(key = "eventKey", name = "要监听的事件KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String eventKey();

    @Field(key = "components", name = "要执行的组件集合（ID或组件配置）", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
    String[] components();
}
