package com.parch.combine.core.component.settings.config;

import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 组件属性设置
 */
public class ComponentPropertySetting {

    private String key;

    private String name;

    private List<String> desc;

    private String refKey;

    private Boolean isRequired;

    private Boolean isArray;

    private String defaultValue;

    private FieldTypeEnum type;

    private List<ComponentPropertyGroupSetting> group;

    private List<ComponentPropertyOptionSetting> options;

    private List<ComponentPropertyEgSetting> egs;

    private List<ComponentPropertySetting> children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public FieldTypeEnum getType() {
        return type;
    }

    public void setType(FieldTypeEnum type) {
        this.type = type;
    }

    public List<ComponentPropertyOptionSetting> getOptions() {
        return options;
    }

    public void setOptions(List<ComponentPropertyOptionSetting> options) {
        this.options = options;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean getIsArray() {
        return isArray;
    }

    public void setIsArray(Boolean array) {
        isArray = array;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<ComponentPropertySetting> getChildren() {
        return children;
    }

    public void setChildren(List<ComponentPropertySetting> children) {
        this.children = children;
    }

    public List<ComponentPropertyGroupSetting> getGroup() {
        return group;
    }

    public void setGroup(List<ComponentPropertyGroupSetting> group) {
        this.group = group;
    }

    public List<ComponentPropertyEgSetting> getEgs() {
        return egs;
    }

    public void setEgs(List<ComponentPropertyEgSetting> egs) {
        this.egs = egs;
    }

    public String getRefKey() {
        return refKey;
    }

    public void setRefKey(String refKey) {
        this.refKey = refKey;
    }
}
