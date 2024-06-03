package com.parch.combine.components.tool.sleep;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface ToolSleepLogicConfig extends ILogicConfig {

    @Field(key = "time", name = "休眠时间（单位毫秒）", type = FieldTypeEnum.BOOLEAN, isRequired = true)
    @FieldEg(eg = "1000", desc = "表示休眠1000毫秒")
    Long time();
}
