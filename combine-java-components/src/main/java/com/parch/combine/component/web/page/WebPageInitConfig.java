package com.parch.combine.component.web.page;

import com.parch.combine.core.base.InitConfig;

/**
 * 初始化配置类
 */
public class WebPageInitConfig extends InitConfig {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
