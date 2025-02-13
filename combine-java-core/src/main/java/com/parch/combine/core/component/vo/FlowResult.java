package com.parch.combine.core.component.vo;

public class FlowResult {

    private Boolean success = true;

    private boolean download = false;

    private String dataFlag;

    private String showMsg;

    private String errMsg;

    private Object data;

    private long runTime;

    private FlowResult() {
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }

    /**
     * 构建
     *
     * @param source 来源数据
     * @return 结果对象
     */
    public static FlowResult build(ComponentDataResult source) {
        FlowResult result = new FlowResult();
        if (source != null) {
            result.setSuccess(source.getSuccess());
            result.setDownload(source.isDownload());
            result.setShowMsg(source.getShowMsg());
            result.setErrMsg(source.getErrMsg());
            result.setData(source.getData());
            result.setDataFlag(source.getDataFlag());
        }
        return result;
    }

    public static FlowResult fail(String showMsg, String errMsg) {
        FlowResult result = new FlowResult();
        result.setSuccess(false);
        result.setShowMsg(showMsg);
        result.setErrMsg(errMsg);
        return result;
    }
}
