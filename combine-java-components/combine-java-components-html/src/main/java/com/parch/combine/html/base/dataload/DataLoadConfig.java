package com.parch.combine.html.base.dataload;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

/**
 * 数据加载配置
 */
public interface DataLoadConfig {

    @Field(key = "cache", order = 0.21F, name = "缓存时间（-1 表示永久）", type = FieldTypeEnum.NUMBER)
    Integer cache();

    @Field(key = "toGlobal", order = 0.22F, name = "是否保存到全局", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean toGlobal();

    @Field(key = "data", order = 0.23F, name = "加载后的数据二次取值表达式", type = FieldTypeEnum.TEXT)
    String data();

    @Field(key = "dataMapping", order = 0.24F, name = "数据映射配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(DataMappingConfig.class)
    DataMappingConfig[] dataMapping();

    interface DataMappingConfig {

        @Field(key = "source", name = "取值表达式", type = FieldTypeEnum.TEXT)
        String source();

        @Field(key = "mappings", name = "数据映射，格式为“旧值:新值”的键值对集合", type = FieldTypeEnum.MAP)
        Map<String, Object> mappings();

        @Field(key = "defaultValue", name = "未命中任何映射时的默认值", type = FieldTypeEnum.TEXT)
        Object defaultValue();
    }
}
