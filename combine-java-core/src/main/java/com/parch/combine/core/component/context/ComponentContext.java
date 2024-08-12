package com.parch.combine.core.component.context;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.vo.ComponentDataResult;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 全局流程解析上下文对象
 *
 * @author parch
 * @date 2023/4/26
 */
public class ComponentContext {

    /**
     * 上下文ID
     */
    private String id;

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
    private Map<String, ComponentDataResult> resultMap;

    /**
     * 最后一个结果（冗余字段）
     */
    private ComponentDataResult lastResult;

    /**
     * 流程中数据
     */
    private Map<String, Object> data;

    /**
     * 当前组件
     */
    private AbstractComponent<?,?> currComponent;

    /**
     * 已经执行过的组件集合
     */
    private List<AbstractComponent<?,?>> executedComponents;

    public ComponentContext() {
        this.id = UUID.randomUUID().toString();
    }

    public static ComponentContext copy(ComponentContext source) {
        ComponentContext context = new ComponentContext();
        context.setFlowKey(source.getFlowKey());
        context.setScopeKey(source.getScopeKey());
        context.setRequestId(source.getRequestId());
        context.setParams(source.getParams());
        context.setHeaders(source.getHeaders());
        context.setResultMap(source.getResultMap());
        context.setData(source.getData());
        context.setExecutedComponents(source.getExecutedComponents());
        context.setVariables(source.getVariables());
        context.setCurrComponent(source.getCurrComponent());
        context.setLastResult(source.getLastResult());
        return context;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, ComponentDataResult> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, ComponentDataResult> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public ComponentDataResult getLastResult() {
        return lastResult;
    }

    public void setLastResult(ComponentDataResult lastResult) {
        this.lastResult = lastResult;
    }

    public AbstractComponent<?, ?> getCurrComponent() {
        return currComponent;
    }

    public void setCurrComponent(AbstractComponent<?, ?> currComponent) {
        this.currComponent = currComponent;
    }

    public List<AbstractComponent<?, ?>> getExecutedComponents() {
        return executedComponents;
    }

    public void setExecutedComponents(List<AbstractComponent<?, ?>> executedComponents) {
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

    public String getId() {
        return id;
    }
}