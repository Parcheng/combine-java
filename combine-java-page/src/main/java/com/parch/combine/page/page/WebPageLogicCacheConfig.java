package com.parch.combine.page.page;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面缓存配置类
 */
public class WebPageLogicCacheConfig extends WebPageLogicConfig {

    @ComponentField(key = "configs", name = "页面元素配置集合", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    private List<HtmlElementConfig> configs;

    public List<HtmlElementConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<HtmlElementConfig> configs) {
        this.configs = configs;
    }
}
