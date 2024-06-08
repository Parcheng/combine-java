package com.parch.combine.core.component.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface IInitConfig {

    @Field(key = "id", name = "组件初始化配置ID", type = FieldTypeEnum.ID)
    @FieldDesc("同一类型的组件可以存在多个初始化配置，可以再逻辑配置中通过ref字段来指定使用哪一个初始化配置")
    @FieldEg(eg = "component_init_config_001")
    String id();

    @Field(key = "type", name = "组件类型", type = FieldTypeEnum.TEXT, isRequired = true)
    String type();
}
