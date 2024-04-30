package com.parch.combine.component.system.template;

import com.parch.combine.core.base.LogicConfig;

import java.util.List;
import java.util.Map;

/**
 * 逻辑配置类
 */
public class SystemTemplateLogicConfig extends LogicConfig {

    private String key;

    private Map<String, Object> configs;

    private List<String> componentIds;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, Object> configs) {
        this.configs = configs;
    }

    public List<String> getComponentIds() {
        return componentIds;
    }

    public void setComponentIds(List<String> componentIds) {
        this.componentIds = componentIds;
    }
}
