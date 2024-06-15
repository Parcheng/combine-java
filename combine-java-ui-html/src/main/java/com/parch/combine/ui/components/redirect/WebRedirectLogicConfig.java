package com.parch.combine.ui.components.redirect;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface WebRedirectLogicConfig extends ILogicConfig {

    @Field(key = "path", name = "重定向地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String path();
}
