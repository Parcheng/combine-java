package com.parch.combine.components.tool.async;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class ToolAsyncLogicConfig extends LogicConfig {

    @Field(key = "components", name = "要执行的逻辑，可以是组件ID，也可以是组件配置", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    private List<Object> components;

    @Override
    public void init() {}

    public List<Object> getComponents() {
        return components;
    }

    public void setComponents(List<Object> components) {
        this.components = components;
    }
}
