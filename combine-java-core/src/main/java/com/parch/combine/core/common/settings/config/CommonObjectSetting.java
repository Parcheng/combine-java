package com.parch.combine.core.common.settings.config;

import java.util.List;

/**
 * 组件公共对象设置
 */
public class CommonObjectSetting {

    private int order;

    private String key;

    private String name;

    private Class<?> classType;

    private List<String> desc;

    private List<PropertySetting> properties;

    private List<ConditionSettings> conditions;

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

    public List<PropertySetting> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertySetting> properties) {
        this.properties = properties;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Class<?> thisClassType() {
        return classType;
    }

    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    public List<ConditionSettings> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionSettings> conditions) {
        this.conditions = conditions;
    }
}
