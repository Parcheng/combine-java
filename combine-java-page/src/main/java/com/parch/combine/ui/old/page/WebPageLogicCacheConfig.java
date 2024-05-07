package com.parch.combine.ui.old.page;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面缓存配置类
 */
public class WebPageLogicCacheConfig extends WebPageLogicConfig {

    @Field(key = "configs", name = "页面元素配置集合", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    private List<HtmlElementConfig> configs;

    public List<HtmlElementConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<HtmlElementConfig> configs) {
        this.configs = configs;
    }
}
