package com.parch.combine.components.tool.semaphore;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class ToolSemaphoreLogicConfig extends LogicConfig {

    @Field(key = "key", name = "限流KEY配置", type = FieldTypeEnum.BOOLEAN, defaultValue = "20")
    @FieldDesc("所有相同的KEY的流程的总流量数不能超过最大值，默认为流程KEY（单接口维度限流）")
    @FieldEg(eg = "user_change", desc = "表示所有 KEY 为 user_change 的流程的流量总和不能超过20")
    private String key;

    @Override
    public void init() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
