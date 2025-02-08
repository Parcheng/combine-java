package com.parch.combine.html.base.group;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ElementGroupLogicConfig extends ILogicConfig {

    @Field(key = "elements", name = "元素组件集合", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    String[] elements();
}
