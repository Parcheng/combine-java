package com.parch.combine.components.web.elements.trigger;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 3, key = WebSettingCanstant.TRIGGER_KEY, name = "加载元素触发配置", desc = "当 TYPE = LOAD 时的参数列表")
public class TriggerLoadEntity extends TriggerEntity {

    @ComponentField(key = "groupId", name = "元素组ID", type = FieldTypeEnum.TEXT, isRequired = true)
    private String groupId;

    @ComponentField(key = "parentId", name = "要写入到的父元素DOM ID", type = FieldTypeEnum.TEXT, isRequired = true)
    private String parentId;

    @ComponentField(key = "params", name = "加载元素组的数据", type = FieldTypeEnum.OBJECT)
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

