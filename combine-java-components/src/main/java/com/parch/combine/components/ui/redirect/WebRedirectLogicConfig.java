package com.parch.combine.components.ui.redirect;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class WebRedirectLogicConfig extends LogicConfig {

    @Field(key = "path", name = "重定向地址", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, isRequired = true)
    private String path;

    @Override
    public void init() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
