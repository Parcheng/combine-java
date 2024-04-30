package com.parch.combine.components.web.elements.trigger;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 配置类
 */
@ComponentCommonObject(order = 3, key = WebSettingCanstant.TRIGGER_KEY, name = "自定义触发配置", desc = "当 TYPE = CUSTOM 时的参数列表")
public class TriggerCustomEntity extends TriggerEntity {

    @ComponentField(key = "functionName", name = "自定义函数名", type = FieldTypeEnum.TEXT, isRequired = true)
    private String functionName;

    @ComponentField(key = "functionParams", name = "自定义函数参数", type = FieldTypeEnum.OBJECT, isArray = true)
    private List<Object> functionParams;

    @ComponentField(key = "toLocalStorage", name = "是否将结果保存到浏览器本地存储", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    @ComponentFieldDesc("缓存KEY为当前“Trigger配置ID”")
    private Boolean toLocalStorage;

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
