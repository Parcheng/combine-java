package com.parch.combine.test.vo;

import com.parch.combine.core.tools.compare.CompareConfig;

/**
 * 检测项VO
 */
public class TestConfigItemVO extends CompareConfig {

    /**
     * 检测项ID
     */
    private String id;

    /**
     * 关联比较项目ID
     */
    private String refId;

    /**
     * 来源值
     */
    private Object sourceValue;

    /**
     * 目标值
     */
    private Object targetValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Object getSourceValue() {
        if (sourceValue == null) {
            sourceValue = parseSourceValue();
        }
        return sourceValue;
    }

    public Object getTargetValue() {
        if (targetValue == null) {
            targetValue = parseTargetValue();
        }
        return targetValue;
    }
}
