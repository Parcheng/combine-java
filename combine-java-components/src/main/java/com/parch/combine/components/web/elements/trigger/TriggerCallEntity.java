package com.parch.combine.components.web.elements.trigger;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

/**
 * 配置类
 */
@ComponentCommonObject(order = 3, key = WebSettingCanstant.TRIGGER_KEY, name = "调用流程触发配置", desc = "当 TYPE = CALL 时的参数列表")
public class TriggerCallEntity extends TriggerEntity {

    @Field(key = "flow", name = "流程KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String flow;

    @Field(key = "fromSubmit", name = "使用FROM表单方式提交", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean fromSubmit;

    @Field(key = "params", name = "流程参数", type = FieldTypeEnum.OBJECT)
    private Object params;

    @Field(key = "headers", name = "请求参数", type = FieldTypeEnum.OBJECT)
    private Map<String, String> headers;

    @Field(key = "localStorageKey", name = "浏览器缓存KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("以该值作为KEY将结果保存到浏览器缓存，不设置该值则不保存")
    private String localStorageKey;

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

    public Boolean getFromSubmit() {
        return fromSubmit;
    }

    public void setFromSubmit(Boolean fromSubmit) {
        this.fromSubmit = fromSubmit;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getLocalStorageKey() {
        return localStorageKey;
    }

    public void setLocalStorageKey(String localStorageKey) {
        this.localStorageKey = localStorageKey;
    }
}
