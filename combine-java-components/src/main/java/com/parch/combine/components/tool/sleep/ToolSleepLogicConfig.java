package com.parch.combine.components.tool.sleep;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class ToolSleepLogicConfig extends LogicConfig {

    @ComponentField(key = "time", name = "休眠时间（单位毫秒）", type = FieldTypeEnum.BOOLEAN, isRequired = true)
    @ComponentFieldEg(eg = "1000", desc = "表示休眠1000毫秒")
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
