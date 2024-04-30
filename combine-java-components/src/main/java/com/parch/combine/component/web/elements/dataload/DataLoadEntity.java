package com.parch.combine.component.web.elements.dataload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.web.elements.enums.DataLoadTypeEnum;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 数据加载配置
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DataLoadEntity.class, name = "REF"),
        @JsonSubTypes.Type(value = FlowDataLoadEntity.class, name = "FLOW"),
        @JsonSubTypes.Type(value = ApiDataLoadEntity.class, name = "URL"),
        @JsonSubTypes.Type(value = FileDataLoadEntity.class, name = "FILE")
})
public class DataLoadEntity {

    private String id;

    private DataLoadTypeEnum type;

    private Integer cache;

    private Boolean toGlobal;

    private String data;

    private List<DataMappingConfig> dataMapping;

    public static class DataMappingConfig {

        private String source;

        private Map<String, Object> mappings;

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
