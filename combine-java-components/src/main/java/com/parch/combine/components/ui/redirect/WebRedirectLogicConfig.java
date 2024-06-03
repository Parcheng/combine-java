package com.parch.combine.components.ui.redirect;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface WebRedirectLogicConfig extends ILogicConfig {

    @Field(key = "path", name = "重定向地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String path();
}
