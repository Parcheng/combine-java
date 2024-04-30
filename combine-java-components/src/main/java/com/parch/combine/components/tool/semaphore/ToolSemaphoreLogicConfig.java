package com.parch.combine.components.tool.semaphore;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class ToolSemaphoreLogicConfig extends LogicConfig {

    @ComponentField(key = "key", name = "限流KEY配置", type = FieldTypeEnum.BOOLEAN, defaultValue = "20")
    @ComponentFieldDesc("所有相同的KEY的流程的总流量数不能超过最大值，默认为流程KEY（单接口维度限流）")
    @ComponentFieldEg(eg = "user_change", desc = "表示所有 KEY 为 user_change 的流程的流量总和不能超过20")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
