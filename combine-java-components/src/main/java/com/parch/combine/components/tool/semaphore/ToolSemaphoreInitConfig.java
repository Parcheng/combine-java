package com.parch.combine.components.tool.semaphore;

import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

/**
 * 初始化配置类
 */
public class ToolSemaphoreInitConfig extends InitConfig {

    @Field(key = "max", name = "最大流量数配置", type = FieldTypeEnum.BOOLEAN, defaultValue = "20")
    @FieldEg(eg = "100", desc = "所有流程最大流量为 100")
    private Integer max;

    @Field(key = "keyMaxes", name = "针对流程KEY设置的最大流量数", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @FieldEg(eg = "{\"user/findAll\": 1000, \"user/update\": 50,}", desc = "user/findAll 流程的最大允许流量未 1000，user/update 流程的最大允许流量未 50")
    private Map<String, Integer> keyMaxes;

    @Override
    public void init() {
        if (getMax() == null) {
            setMax(20);
        }
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Map<String, Integer> getKeyMaxes() {
        return keyMaxes;
    }

    public void setKeyMaxes(Map<String, Integer> keyMaxes) {
        this.keyMaxes = keyMaxes;
    }
}
