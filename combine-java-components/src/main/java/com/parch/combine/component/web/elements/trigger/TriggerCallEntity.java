package com.parch.combine.component.web.elements.trigger;

/**
 * 配置类
 */
public class TriggerCallEntity extends TriggerEntity {

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
