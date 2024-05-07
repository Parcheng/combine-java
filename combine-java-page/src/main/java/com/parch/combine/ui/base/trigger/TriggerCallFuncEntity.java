package com.parch.combine.ui.base.trigger;


import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 3, key = WebSettingCanstant.TRIGGER_KEY, name = "调用页面元素函数触发配置", desc = "当 TYPE = CALL_FUNC 时的参数列表")
public class TriggerCallFuncEntity extends TriggerEntity {

    @Field(key = "id", name = "页面元素ID", type = FieldTypeEnum.TEXT, isRequired = true)
    private String id;

    @Field(key = "name", name = "函数名称", type = FieldTypeEnum.TEXT, isRequired = true)
    private String name;

    @Field(key = "params", name = "函数参数", type = FieldTypeEnum.OBJECT)
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
