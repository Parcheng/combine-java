package com.parch.combine.components.tool.async;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ToolAsyncLogicConfig extends ILogicConfig {

    @Field(key = "components", name = "要执行的逻辑，可以是组件ID，也可以是组件配置", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    String[] components();
}
