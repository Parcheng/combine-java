package com.parch.combine.core.settings.config;

import com.parch.combine.core.base.AbsComponent;

import java.util.List;

/**
 * 组件设置
 */
public class ComponentSetting {

    private Class<? extends AbsComponent<?, ?>> componentClass;

    private String key;

    private String name;

    private List<String> desc;

    private List<String> important;

    private List<ComponentPropertySetting> initConfig;

    private List<ComponentPropertySetting> logicConfig;

    private List<ComponentCommonObjectSetting> commonObjects;

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

    public List<ComponentPropertySetting> getInitConfig() {
        return initConfig;
    }

    public void setInitConfig(List<ComponentPropertySetting> initConfig) {
        this.initConfig = initConfig;
    }

    public void addInitConfig(ComponentPropertySetting initConfig) {
        this.initConfig.add(initConfig);
    }

    public List<ComponentPropertySetting> getLogicConfig() {
        return logicConfig;
    }

    public void setLogicConfig(List<ComponentPropertySetting> logicConfig) {
        this.logicConfig = logicConfig;
    }

    public void addLogicConfig(ComponentPropertySetting logicConfig) {
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

    public Class<? extends AbsComponent<?, ?>> getComponentClass() {
        return componentClass;
    }

    public void setComponentClass(Class<? extends AbsComponent<?, ?>> componentClass) {
        this.componentClass = componentClass;
    }

    public List<ComponentCommonObjectSetting> getCommonObjects() {
        return commonObjects;
    }

    public void setCommonObjects(List<ComponentCommonObjectSetting> commonObjects) {
        this.commonObjects = commonObjects;
    }
}
