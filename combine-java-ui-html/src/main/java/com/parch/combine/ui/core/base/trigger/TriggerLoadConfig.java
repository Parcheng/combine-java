package com.parch.combine.ui.core.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;

import java.util.List;

/**
 * 配置类
 */
@CommonObject(order = 3, name = "加载元素触发配置", desc = "当 TYPE = LOAD 时的参数列表")
public class TriggerLoadConfig extends TriggerConfig {

    @Field(key = "groupId", name = "元素组ID", type = FieldTypeEnum.TEXT, isRequired = true)
    private String groupId;

    @Field(key = "parentId", name = "要写入到的父元素DOM ID", type = FieldTypeEnum.TEXT, isRequired = true)
    private String parentId;

    @Field(key = "params", name = "加载元素组的数据", type = FieldTypeEnum.ANY)
    private Object params;

    @Override
    public void init() {}

    @Override
    public List<String> check() {
        return null;
    }

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

