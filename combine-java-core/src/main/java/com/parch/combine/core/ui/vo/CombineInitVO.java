package com.parch.combine.core.ui.vo;

import java.util.List;

public class CombineInitVO {

    private String id;

    private boolean success;

    private List<String> elementIds;

    private List<String> errorList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
