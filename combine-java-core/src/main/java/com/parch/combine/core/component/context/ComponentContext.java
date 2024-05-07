package com.parch.combine.core.component.context;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.vo.DataResult;

import java.util.List;
import java.util.Map;

/**
 * 全局流程解析上下文对象
 *
 * @author parch
 * @date 2023/4/26
 */
public class ComponentContext {

    /**
     * 作用域Key
     */
    private String scopeKey;

    /**
     * 流程Key
     */
    private String flowKey;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 入参数据
     */
    private Map<String, Object> params;

    /**
     * 请求头数据
     */
    private Map<String, String> headers;

    /**
     * 流程中变量
     */
    private Map<String, Object> variables;

    /**
     * 组件结果数据
     */
    private Map<String, DataResult> resultMap;

    /**
     * 最后一个结果（冗余字段）
     */
    private DataResult lastResult;

    /**
     * 流程中数据
     */
    private Map<String, Object> data;

    /**
     * 当前组件
     */
    private AbsComponent<?,?> currComponent;

    /**
     * 已经执行过的组件集合
     */
    private List<AbsComponent<?,?>> executedComponents;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, DataResult> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, DataResult> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public DataResult getLastResult() {
        return lastResult;
    }

    public void setLastResult(DataResult lastResult) {
        this.lastResult = lastResult;
    }

    public AbsComponent<?, ?> getCurrComponent() {
        return currComponent;
    }

    public void setCurrComponent(AbsComponent<?, ?> currComponent) {
        this.currComponent = currComponent;
    }

    public List<AbsComponent<?, ?>> getExecutedComponents() {
        return executedComponents;
    }

    public void setExecutedComponents(List<AbsComponent<?, ?>> executedComponents) {
        this.executedComponents = executedComponents;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getScopeKey() {
        return scopeKey;
    }

    public void setScopeKey(String scopeKey) {
        this.scopeKey = scopeKey;
    }
}