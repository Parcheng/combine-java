package com.parch.combine.page.redirect;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class WebRedirectLogicConfig extends LogicConfig {

    @ComponentField(key = "path", name = "重定向地址", type = FieldTypeEnum.TEXT, isRequired = true)
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
