package com.parch.combine.tool.base.semaphore;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ToolSemaphoreLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "限流KEY配置", type = FieldTypeEnum.TEXT)
    @FieldDesc("所有相同的KEY的流程的总流量数不能超过最大值，默认为流程KEY（单接口维度限流）")
    @FieldEg(eg = "user_change", desc = "表示所有 KEY 为 user_change 的流程的流量总和不能超过20")
    String key();
}
