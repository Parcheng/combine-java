package com.parch.combine.component.system.template;

import com.parch.combine.core.base.InitConfig;

import java.util.List;
import java.util.Map;

/**
 * 初始化配置类
 */
public class SystemTemplateInitConfig extends InitConfig {

    private String variableKey;

    private Map<String, List<Map<String, Object>>> templates;

    public Map<String, List<Map<String, Object>>> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, List<Map<String, Object>>> templates) {
        this.templates = templates;
    }

    public String getVariableKey() {
        return variableKey;
    }

    public void setVariableKey(String variableKey) {
        this.variableKey = variableKey;
    }
}
