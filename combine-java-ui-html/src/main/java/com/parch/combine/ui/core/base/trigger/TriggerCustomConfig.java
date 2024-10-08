package com.parch.combine.ui.core.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;

import java.util.List;

/**
 * 配置类
 */
@CommonObject(order = 3, name = "自定义触发配置", desc = "当 TYPE = CUSTOM 时的参数列表")
public class TriggerCustomConfig extends TriggerConfig {

    @Field(key = "functionName", name = "自定义函数名", type = FieldTypeEnum.TEXT, isRequired = true)
    private String functionName;

    @Field(key = "functionParams", name = "自定义函数参数", type = FieldTypeEnum.ANY, isArray = true)
    private List<Object> functionParams;

    @Field(key = "toLocalStorage", name = "是否将结果保存到浏览器本地存储", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    @FieldDesc("缓存KEY为当前“Trigger配置ID”")
    private Boolean toLocalStorage;

    @Override
    public void init() {}

    @Override
    public List<String> check() {
        return null;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Object> getFunctionParams() {
        return functionParams;
    }

    public void setFunctionParams(List<Object> functionParams) {
        this.functionParams = functionParams;
    }

    public Boolean getToLocalStorage() {
        return toLocalStorage;
    }

    public void setToLocalStorage(Boolean toLocalStorage) {
        this.toLocalStorage = toLocalStorage;
    }
}
