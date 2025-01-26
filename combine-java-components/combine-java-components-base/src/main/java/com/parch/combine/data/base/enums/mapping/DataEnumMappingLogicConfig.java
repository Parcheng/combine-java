package com.parch.combine.data.base.enums.mapping;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

/**
 * 逻辑配置类
 */
public interface DataEnumMappingLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    Object source();

    @Field(key = "resultId", name = "输出指定组件ID的执行结果", type = FieldTypeEnum.TEXT)
    @FieldDesc("默认输出 source 的数据")
    String resultId();

    @Field(key = "items", name = "映射配置集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(MappingItem.class)
    MappingItem[] items();

    interface MappingItem {

        @Field(key = "enumKey", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
        String enumKey();

        @Field(key = "sourceField", name = "数据来源字段名", type = FieldTypeEnum.TEXT, isRequired = true)
        String sourceField();

        @Field(key = "targetField", name = "目标字段名", type = FieldTypeEnum.TEXT)
        String targetField();
    }
}
