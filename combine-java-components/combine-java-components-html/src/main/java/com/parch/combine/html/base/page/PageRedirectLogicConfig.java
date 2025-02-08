package com.parch.combine.html.base.page;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface PageRedirectLogicConfig extends ILogicConfig {

    @Field(key = "linkTo", name = "重定向地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String linkTo();
}
