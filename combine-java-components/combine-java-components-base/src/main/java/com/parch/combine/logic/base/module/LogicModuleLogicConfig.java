package com.parch.combine.logic.base.module;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface LogicModuleLogicConfig extends ILogicConfig {

    @Field(key = "components", name = "要执行的逻辑", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    String[] components();

    @Field(key = "out", name = "输出结果", type = FieldTypeEnum.ANY, parseExpression = false)
    @FieldDesc("默认为 components 中最后一个组件的执行结果")
    Object out();

}
