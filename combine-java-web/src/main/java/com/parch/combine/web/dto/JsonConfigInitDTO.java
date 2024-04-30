package com.parch.combine.web.dto;

import java.util.List;

public class JsonConfigInitDTO {

    private boolean success;

    private String key;

    private List<String> errorList;

    private List<String> componentIds;

    private List<String> staticComponentIds;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getComponentIds() {
        return componentIds;
    }

    public void setComponentIds(List<String> componentIds) {
        this.componentIds = componentIds;
    }

    public List<String> getStaticComponentIds() {
        return staticComponentIds;
    }

    public void setStaticComponentIds(List<String> staticComponentIds) {
        this.staticComponentIds = staticComponentIds;
    }
}
