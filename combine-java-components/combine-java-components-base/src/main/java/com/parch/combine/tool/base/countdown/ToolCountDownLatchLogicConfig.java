package com.parch.combine.tool.base.countdown;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ToolCountDownLatchLogicConfig extends ILogicConfig {

    @Field(key = "components", name = "主分支执行组件集合", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    @FieldDesc("要执行的逻辑，可以是组件ID，也可以是组件配")
    String[] components();

    @Field(key = "items", name = "CountDown分支配置集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(ItemConfig.class)
    ItemConfig[] items();

    interface ItemConfig {

        @Field(key = "components", name = "子分支执行组件集合", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
        @FieldDesc("要执行的逻辑，可以是组件ID，也可以是组件配")
        String[] components();
    }
}
