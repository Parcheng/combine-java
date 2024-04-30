package com.parch.combine.component.call;

import com.parch.combine.core.base.LogicConfig;

import java.util.Map;

/**
 * 逻辑配置类
 */
public class CallLogicConfig extends LogicConfig {

    /**
     * 地址
     */
    private String url;

    /**
     * 参数
     */
    private Map<String, Object> params;

    /**
     * 请求头
     */
    private Map<String, String> headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
