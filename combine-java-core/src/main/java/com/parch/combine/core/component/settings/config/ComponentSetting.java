package com.parch.combine.core.component.settings.config;

import com.parch.combine.core.common.settings.config.CommonObjectSetting;
import com.parch.combine.core.common.settings.config.PropertySetting;
import com.parch.combine.core.component.base.AbstractComponent;

import java.util.List;

/**
 * 组件设置
 */
public class ComponentSetting {

    private Class<? extends AbstractComponent<?, ?>> componentClass;

    private int order;

    private String key;

    private String name;

    private List<String> desc;

    private List<String> important;

    private List<PropertySetting> initConfig;

    private List<PropertySetting> logicConfig;

    private ComponentResultSetting result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public List<String> getImportant() {
        return important;
    }

    public void setImportant(List<String> important) {
        this.important = important;
    }

    public List<PropertySetting> getInitConfig() {
        return initConfig;
    }

    public void setInitConfig(List<PropertySetting> initConfig) {
        this.initConfig = initConfig;
    }

    public void addInitConfig(PropertySetting initConfig) {
        this.initConfig.add(initConfig);
    }

    public List<PropertySetting> getLogicConfig() {
        return logicConfig;
    }

    public void setLogicConfig(List<PropertySetting> logicConfig) {
        this.logicConfig = logicConfig;
    }

    public void addLogicConfig(PropertySetting logicConfig) {
        this.logicConfig.add(logicConfig);
    }

    public ComponentResultSetting getResult() {
        return result;
    }

    public void setResult(ComponentResultSetting result) {
        this.result = result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setComponentClass(Class<? extends AbstractComponent<?, ?>> componentClass) {
        this.componentClass = componentClass;
    }

    public Class<? extends AbstractComponent<?, ?>> thisComponentClass() {
        return componentClass;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
