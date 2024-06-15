package com.parch.combine.components.data.general.mapping;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

/**
 * 逻辑配置类
 */
public interface DataMappingLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "映射配置项集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataMappingItem.class)
    @FieldEg(eg = "{\"fieldName\":\"id\",\"source\":\"#{id}\"}", desc = "将入参的 id 参数的值，赋值给执行结果的 id 字段")
    @FieldEg(eg = "{\"fieldName\":\"name\",\"source\":\"#{$r.data001.name}\"}", desc = "将 data001 组件返回结果的 name 字段的值，赋值给执行结果的 name 字段")
    @FieldEg(eg = "{\"fieldName\":\"age\",\"source\":\"18\"}", desc = "将 18 赋值给执行结果的 age 字段")
    DataMappingItem[] items();

    interface DataMappingItem {

        @Field(key = "fieldName", name = "新字段名", type = FieldTypeEnum.TEXT, isRequired = true)
        String fieldName();

        @Field(key = "source", name = "数据源/数据值", type = FieldTypeEnum.TEXT, isRequired = true)
        String source();
    }
}
