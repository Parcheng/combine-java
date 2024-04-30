package com.parch.combine.core.settings.config;

import java.util.List;

/**
 * 组件分类设置
 */
public class ComponentClassifySetting {

    private String key;

    private String name;

    private List<ComponentSetting> settings;

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

    public List<ComponentSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<ComponentSetting> settings) {
        this.settings = settings;
    }
}
