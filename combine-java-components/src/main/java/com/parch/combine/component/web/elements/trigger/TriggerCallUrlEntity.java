package com.parch.combine.component.web.elements.trigger;

import java.util.Map;

/**
 * 配置类
 */
public class TriggerCallUrlEntity extends TriggerEntity {

    private String url;

    private String mode;

    private Object params;

    private Map<String, String> headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
