package com.parch.combine.ui.core.context;

public class ConfigLoadingContext {

    private String scopeKey;

    private String systemUrl;

    private String baseUrl;

    private String flagConfigsJson;

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

    public String getScopeKey() {
        return scopeKey;
    }

    public void setScopeKey(String scopeKey) {
        this.scopeKey = scopeKey;
    }

    public String getFlagConfigsJson() {
        return flagConfigsJson;
    }

    public void setFlagConfigsJson(String flagConfigsJson) {
        this.flagConfigsJson = flagConfigsJson;
    }
}