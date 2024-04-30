package com.parch.combine.component.web.page.custom;

import com.parch.combine.component.web.page.WebLogicConfig;
import com.parch.combine.component.web.page.WebPageTempConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class PageCustomLogicConfig extends WebLogicConfig {

    private List<WebPageTempConfig.HtmlElementConfig<HtmlElement>> configs;

    public List<WebPageTempConfig.HtmlElementConfig<HtmlElement>> getConfigs() {
        return configs;
    }

    public void setConfigs(List<WebPageTempConfig.HtmlElementConfig<HtmlElement>> configs) {
        this.configs = configs;
    }
}
