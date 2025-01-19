package com.parch.combine.logic.base.exception;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface LogicExceptionLogicConfig extends ILogicConfig {

    @Field(key = "components", name = "要执行的组件集合", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    @FieldDesc("要执行的组件集合，如果这些组件执行出现异常，则会输出指定的错误信息")
    String[] components();

    @Field(key = "finallyComponents", name = "必须要执行的组件集合", type = FieldTypeEnum.COMPONENT, isArray = true)
    @FieldDesc("无论 components 中组件是否执行出错，该配置的下的组件一定会被执行")
    String[] finallyComponents();

    @Field(key = "msg", name = "错误信息", type = FieldTypeEnum.TEXT)
    @FieldDesc("组件执行出现错误时要输出的错误信息，默认输出原错误信息")
    String msg();
}
