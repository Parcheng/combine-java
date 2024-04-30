package com.parch.combine.component.web.elements.dataload;

/**
 * 数据加载配置
 */
public class FlowDataLoadEntity extends DataLoadEntity {

    private String flow;

    private Object params;

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
