package com.parch.combine.ui.core.settings.config;

import java.util.List;

public class PageElementClassifySetting {

    private String key;

    private String name;

    private List<PageElementSetting> settings;

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

    public List<PageElementSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<PageElementSetting> settings) {
        this.settings = settings;
    }
}
