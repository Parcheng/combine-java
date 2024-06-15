package com.parch.combine.ui.core.vo;

import java.util.List;

public class CombineRegisterVO {

    private boolean success;

    private CombineLoadVO loadResult;

    private List<String> buildErrorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CombineLoadVO getLoadResult() {
        return loadResult;
    }

    public void setLoadResult(CombineLoadVO loadResult) {
        this.loadResult = loadResult;
    }

    public List<String> getBuildErrorMsg() {
        return buildErrorMsg;
    }

    public void setBuildErrorMsg(List<String> buildErrorMsg) {
        this.buildErrorMsg = buildErrorMsg;
    }
}
