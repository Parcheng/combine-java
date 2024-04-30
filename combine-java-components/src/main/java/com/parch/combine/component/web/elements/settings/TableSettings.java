package com.parch.combine.component.web.elements.settings;

import com.parch.combine.component.web.elements.entity.ElementEntity;

import java.util.List;

/**
 * 页面元素设置
 */
public class TableSettings extends BaseSettings{

    private List<String> fieldNames;

    private List<String> headNames;

    private Integer minLength;

    private Boolean hasChecked;

    private Boolean hasIndex;

    public ElementEntity<?> rowOpts;

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<String> getHeadNames() {
        return headNames;
    }

    public void setHeadNames(List<String> headNames) {
        this.headNames = headNames;
    }

    public ElementEntity<?> getRowOpts() {
        return rowOpts;
    }

    public void setRowOpts(ElementEntity<?> rowOpts) {
        this.rowOpts = rowOpts;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Boolean getHasChecked() {
        return hasChecked;
    }

    public void setHasChecked(Boolean hasChecked) {
        this.hasChecked = hasChecked;
    }

    public Boolean getHasIndex() {
        return hasIndex;
    }

    public void setHasIndex(Boolean hasIndex) {
        this.hasIndex = hasIndex;
    }
}
