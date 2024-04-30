package com.parch.combine.components.web.page;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面缓存配置类
 */
public class WebPageLogicCacheConfig extends WebPageLogicConfig {

    @ComponentField(key = "configs", name = "页面元素配置集合", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    private List<WebPageLogicConfig.HtmlElementConfig> configs;

    public List<WebPageLogicConfig.HtmlElementConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<WebPageLogicConfig.HtmlElementConfig> configs) {
        this.configs = configs;
    }
}
