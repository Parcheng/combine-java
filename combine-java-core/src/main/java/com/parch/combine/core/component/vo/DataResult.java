package com.parch.combine.core.component.vo;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.base.AbsComponent;

import java.util.HashMap;
import java.util.Map;

public class DataResult {

    private String id;

    private Boolean success = true;

    private boolean download = false;

    private boolean stop = false;

    private String showMsg;

    private String errMsg;

    private String dataFlag;

    private Object data;

    private DataResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (CheckEmptyUtil.isEmpty(id)) {
            id = System.currentTimeMillis() + "";
        }
        this.id = id;
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

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }

    /**
     * 文件下载
     *
     * @param data 结果数据
     * @return 结果对象
     */
    public static DataResult download(Object data) {
        DataResult result = success(data);
        result.setDownload(true);
        return result;
    }

    /**
     * 成功
     *
     * @param data 结果数据
     * @return 结果对象
     */
    public static DataResult success(Object data) {
        DataResult result = build();
        result.setSuccess(true);
        result.setData(data);
        result.setErrMsg(null);
        return result;
    }

    /**
     * 成功
     *
     * @param data 结果数据
     * @return 结果对象
     */
    public static DataResult successAndStop(Object data) {
        DataResult result = success(data);
        result.setStop(true);
        return result;
    }

    /**
     * 成功
     *
     * @param data 结果数据
     * @param currPage 当前页
     * @param pageSize 每也条数
     * @param totalCount 数据条数
     * @return 结果对象
     */
    public static DataResult success(Object data, int currPage, int pageSize, int totalCount) {
        DataResult result = build();
        result.setSuccess(true);
        result.setErrMsg(null);

        Map<String, Object> pageData = new HashMap<>();
        pageData.put("page", currPage);
        pageData.put("pageSize", pageSize);
        pageData.put("totalCount", totalCount);

        int maxPage = totalCount / pageSize;
        maxPage = totalCount % pageSize == 0 ? maxPage : maxPage + 1;
        pageData.put("maxPage", maxPage);
        pageData.put("data", data);

        result.setData(pageData);
        return result;
    }

    /**
     * 失败
     *
     * @param error 错误信息
     * @return 结果对象
     */
    public static DataResult fail(IComponentError error, Object ... msgParams) {
        DataResult result = build();
        result.setSuccess(false);
        result.setErrMsg(String.format(error.getMsg(), msgParams));
        result.setShowMsg(String.format(error.getShowMsg(), msgParams));
        return result;
    }

    /**
     * 失败
     *
     * @param data 数据
     * @param error 错误信息
     * @return 结果对象
     */
    public static DataResult fail(Object data, IComponentError error, Object ... msgParams) {
        DataResult result = fail(error, msgParams);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     *
     * @param msg 错误信息
     * @param showMsg 显示错误信息
     * @return 结果对象
     */
    public static DataResult fail(String msg, String showMsg) {
        DataResult result = new DataResult();
        result.setSuccess(false);
        result.setErrMsg(msg);
        result.setShowMsg(showMsg);
        return result;
    }

    /**
     * 构建 DataResult 对象
     *
     * @return DataResult对象
     */
    private static DataResult build() {
        DataResult result = new DataResult();

        AbsComponent<?, ?> component = ComponentContextHandler.getCurrComponent();
        if (component != null) {
            result.setId(component.getId());
            result.setErrMsg("【" + component.getId() + "】【" + component.getType() + "】");
        }

        return result;
    }
}
