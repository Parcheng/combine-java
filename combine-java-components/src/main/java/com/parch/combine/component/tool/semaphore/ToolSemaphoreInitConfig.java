package com.parch.combine.component.tool.semaphore;

import com.parch.combine.core.base.InitConfig;

import java.util.Map;

/**
 * 初始化配置类
 */
public class ToolSemaphoreInitConfig extends InitConfig {

    private Integer max;

    private Map<String, Integer> keyMaxes;

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
