package com.parch.combine.core.component.tools.compare;

/**
 * 检测项结果
 */
public class CompareResult {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String errorMsg;

    private CompareResult() { }

    public static CompareResult success() {
        CompareResult result = new CompareResult();
        result.setSuccess(true);
        return result;
    }

    public static CompareResult fail() {
        CompareResult result = new CompareResult();
        result.setSuccess(false);
        return result;
    }

    public static CompareResult error(String errorMsg) {
        CompareResult result = new CompareResult();
        result.setSuccess(false);
        result.setErrorMsg(errorMsg);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
