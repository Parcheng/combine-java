package com.parch.combine.core.ui.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

import java.util.List;
import java.util.Map;

/**
 * 配置类
 */
@CommonObject(order = 3, key = PageSettingCanstant.TRIGGER_KEY, name = "调用URL触发配置", desc = "当 TYPE = CALL_URL 时的参数列表")
public class TriggerCallUrlConfig extends TriggerConfig {

    @Field(key = "url", name = "URL地址", type = FieldTypeEnum.TEXT, isRequired = true)
    private String url;

    @Field(key = "mode", name = "请求方式 POST | GET", type = FieldTypeEnum.TEXT, isRequired = true)
    private String mode;

    @Field(key = "fromSubmit", name = "使用FROM表单方式提交", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean fromSubmit;

    @Field(key = "headers", name = "请求头", type = FieldTypeEnum.OBJECT)
    private Object params;

    @Field(key = "params", name = "请求参数", type = FieldTypeEnum.OBJECT)
    private Map<String, String> headers;

    @Field(key = "localStorageKey", name = "浏览器缓存KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("以该值作为KEY将结果保存到浏览器缓存，不设置该值则不保存")
    private String localStorageKey;

    @Override
    public void init() {}

    @Override
    public List<String> check() {
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Boolean getFromSubmit() {
        return fromSubmit;
    }

    public void setFromSubmit(Boolean fromSubmit) {
        this.fromSubmit = fromSubmit;
    }

    public String getLocalStorageKey() {
        return localStorageKey;
    }

    public void setLocalStorageKey(String localStorageKey) {
        this.localStorageKey = localStorageKey;
    }
}
