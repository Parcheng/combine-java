package com.parch.combine.component.web.elements.trigger;


/**
 * 配置类
 */
public class TriggerCallFuncEntity extends TriggerEntity {

    private String id;

    private String name;

    private Object params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
