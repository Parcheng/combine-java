package com.parch.combine.core.common.settings.config;


import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.config.IOptionSetting;

import java.util.List;

/**
 * 组件属性组设置
 */
public class PropertyGroupSetting {

    private Integer index;

    private String name;

    private FieldTypeEnum type;

    private Boolean isRequired;

    private Boolean hasExpression;

    private List<IOptionSetting> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean required) {
        isRequired = required;
    }

    public FieldTypeEnum getType() {
        return type;
    }

    public void setType(FieldTypeEnum type) {
        this.type = type;
    }

    public List<IOptionSetting> getOptions() {
        return options;
    }

    public void setOptions(List<IOptionSetting> options) {
        this.options = options;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getHasExpression() {
        return hasExpression;
    }

    public void setHasExpression(Boolean hasExpression) {
        this.hasExpression = hasExpression;
    }
}
