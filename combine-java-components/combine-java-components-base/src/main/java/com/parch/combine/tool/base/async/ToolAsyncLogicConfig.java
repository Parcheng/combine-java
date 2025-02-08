package com.parch.combine.tool.base.async;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ToolAsyncLogicConfig extends ILogicConfig {

    @Field(key = "components", name = "要执行的逻辑，可以是组件ID，也可以是组件配置", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    String[] components();
}
