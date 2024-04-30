package com.parch.combine.component.web.elements.trigger;

/**
 * 配置类
 */
public class TriggerLoadEntity extends TriggerEntity {

    private String groupId;

    private String parentId;

    private Object params;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}

