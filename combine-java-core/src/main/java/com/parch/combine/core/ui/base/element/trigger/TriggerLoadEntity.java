package com.parch.combine.core.ui.base.element.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

/**
 * 配置类
 */
@CommonObject(order = 3, key = PageSettingCanstant.TRIGGER_KEY, name = "加载元素触发配置", desc = "当 TYPE = LOAD 时的参数列表")
public class TriggerLoadEntity extends TriggerEntity {

    @Field(key = "groupId", name = "元素组ID", type = FieldTypeEnum.TEXT, isRequired = true)
    private String groupId;

    @Field(key = "parentId", name = "要写入到的父元素DOM ID", type = FieldTypeEnum.TEXT, isRequired = true)
    private String parentId;

    @Field(key = "params", name = "加载元素组的数据", type = FieldTypeEnum.OBJECT)
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

