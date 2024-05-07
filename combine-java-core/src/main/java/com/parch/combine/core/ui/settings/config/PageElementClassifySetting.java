package com.parch.combine.core.ui.settings.config;

import java.util.List;

/**
 * 组件分类设置
 */
public class PageElementClassifySetting {

    private String key;

    private String name;

    private List<Object> settings;

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

    public List<Object> getSettings() {
        return settings;
    }

    public void setSettings(List<Object> settings) {
        this.settings = settings;
    }
}