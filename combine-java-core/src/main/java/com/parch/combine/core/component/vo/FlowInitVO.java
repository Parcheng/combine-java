package com.parch.combine.core.component.vo;

import java.util.List;

/**
 * 流程初始化VO
 */
public class FlowInitVO {

    private String flowKey;

    private boolean success;

    private List<String> componentIds;

    private List<String> staticComponentIds;

    private List<String> errorList;

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getComponentIds() {
        return componentIds;
    }

    public void setComponentIds(List<String> componentIds) {
        this.componentIds = componentIds;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getStaticComponentIds() {
        return staticComponentIds;
    }

    public void setStaticComponentIds(List<String> staticComponentIds) {
        this.staticComponentIds = staticComponentIds;
    }
}
