package com.parch.combine.core.ui.context;

import com.parch.combine.core.ui.manager.CombineManager;

public class ConfigLoadingContext {

    private String systemUrl;

    private String baseUrl;

    private CombineManager manager;

    public String getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public CombineManager getManager() {
        return manager;
    }

    public void setManager(CombineManager manager) {
        this.manager = manager;
    }
}