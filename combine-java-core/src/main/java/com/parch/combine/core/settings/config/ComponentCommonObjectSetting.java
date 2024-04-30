package com.parch.combine.core.settings.config;

import java.util.List;

/**
 * 组件公共对象设置
 */
public class ComponentCommonObjectSetting {

    private String key;

    private String name;

    private List<String> desc;

    private List<ComponentPropertySetting> properties;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public List<ComponentPropertySetting> getProperties() {
        return properties;
    }

    public void setProperties(List<ComponentPropertySetting> properties) {
        this.properties = properties;
    }
}
