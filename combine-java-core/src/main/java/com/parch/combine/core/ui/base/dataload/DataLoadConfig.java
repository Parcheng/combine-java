package com.parch.combine.core.ui.base.dataload;

import com.parch.combine.core.common.base.ICheck;
import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 数据加载配置
 */
public abstract class DataLoadConfig implements IInit, ICheck {

    @Field(key = "id", name = "ID", type = FieldTypeEnum.ID)
    private String id;

    @Field(key = "type", name = "加载类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = DataLoadTypeEnum.class)
    private DataLoadTypeEnum type;

    @Field(key = "cache", name = "缓存时间（-1 表示永久）", type = FieldTypeEnum.NUMBER)
    private Integer cache;

    @Field(key = "toGlobal", name = "是否保存到全局", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean toGlobal;

    @Field(key = "data", name = "加载后的数据二次取值表达式", type = FieldTypeEnum.TEXT, defaultValue = "随机生成")
    private String data;

    @Field(key = "dataMapping", name = "数据映射配置集合", type = FieldTypeEnum.CONFIG, isArray = true, defaultValue = "随机生成")
    @FieldObject(DataMappingConfig.class)
    private List<DataMappingConfig> dataMapping;

    public static class DataMappingConfig {

        @Field(key = "source", name = "取值表达式", type = FieldTypeEnum.TEXT)
        private String source;

        @Field(key = "mappings", name = "数据映射，格式为“旧值:新值”的键值对集合", type = FieldTypeEnum.MAP)
        private Map<String, Object> mappings;

        @Field(key = "defaultValue", name = "未命中任何映射时的默认值", type = FieldTypeEnum.TEXT)
        private Object defaultValue;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Map<String, Object> getMappings() {
            return mappings;
        }

        public void setMappings(Map<String, Object> mappings) {
            this.mappings = mappings;
        }

        public Object getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Object defaultValue) {
            this.defaultValue = defaultValue;
        }
    }

    public String getId() {
        if (CheckEmptyUtil.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataLoadTypeEnum getType() {
        return type;
    }

    public void setType(String type) {
        this.type = DataLoadTypeEnum.get(type);
    }

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getToGlobal() {
        return toGlobal;
    }

    public void setToGlobal(Boolean toGlobal) {
        this.toGlobal = toGlobal;
    }

    public void setType(DataLoadTypeEnum type) {
        this.type = type;
    }

    public List<DataMappingConfig> getDataMapping() {
        return dataMapping;
    }

    public void setDataMapping(List<DataMappingConfig> dataMapping) {
        this.dataMapping = dataMapping;
    }
}
