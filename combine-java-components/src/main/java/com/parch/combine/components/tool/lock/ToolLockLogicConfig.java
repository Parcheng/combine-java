package com.parch.combine.components.tool.lock;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface ToolLockLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "锁的KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("不设置该值时，默认所有流程共用一把锁")
    @FieldEg(eg = "user_change", desc = "添加或解除 KEY 为 user_change 的锁")
    String key();

    @Field(key = "count", name = "加锁/解锁", type = FieldTypeEnum.NUMBER, isRequired = true)
    @FieldDesc("大于0表示加锁，小于0表示解锁")
    Integer count();
}
