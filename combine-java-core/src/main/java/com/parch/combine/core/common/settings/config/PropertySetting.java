package com.parch.combine.core.common.settings.config;

import java.util.List;

/**
 * 组件属性设置
 */
public class PropertySetting {

    private String key;

    private String name;

    private List<String> desc;

    private float order;

    private String refKey;

    private Boolean isRequired;

    private Boolean isArray;

    private String defaultValue;

    private FieldTypeEnum type;

    private List<PropertyGroupSetting> group;

    private List<PropertyOptionSetting> options;

    private List<PropertyEgSetting> egs;

    private List<PropertySetting> children;

    private List<String> refCommonKeys;

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

    public List<PropertyOptionSetting> getOptions() {
        return options;
    }

    public void setOptions(List<PropertyOptionSetting> options) {
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

    public List<PropertySetting> getChildren() {
        return children;
    }

    public void setChildren(List<PropertySetting> children) {
        this.children = children;
    }

    public List<PropertyGroupSetting> getGroup() {
        return group;
    }

    public void setGroup(List<PropertyGroupSetting> group) {
        this.group = group;
    }

    public List<PropertyEgSetting> getEgs() {
        return egs;
    }

    public void setEgs(List<PropertyEgSetting> egs) {
        this.egs = egs;
    }

    public String getRefKey() {
        return refKey;
    }

    public void setRefKey(String refKey) {
        this.refKey = refKey;
    }

    public List<String> getRefCommonKeys() {
        return refCommonKeys;
    }

    public void setRefCommonKeys(List<String> refCommonKeys) {
        this.refCommonKeys = refCommonKeys;
    }

    public float getOrder() {
        return order;
    }

    public void setOrder(float order) {
        this.order = order;
    }
}
