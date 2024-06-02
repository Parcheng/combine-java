package com.parch.combine.core.component.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface IInitConfig {

    @Field(key = "id", name = "组件初始化配置ID", type = FieldTypeEnum.ID)
    String id();

    @Field(key = "type", name = "组件类型", type = FieldTypeEnum.TEXT, isRequired = true)
    String type();
}
