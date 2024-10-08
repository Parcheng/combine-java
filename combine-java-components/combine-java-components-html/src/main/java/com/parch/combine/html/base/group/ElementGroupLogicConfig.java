package com.parch.combine.html.base.group;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface ElementGroupLogicConfig extends ILogicConfig {

    @Field(key = "elements", name = "元素组件集合", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    String[] elements();
}
