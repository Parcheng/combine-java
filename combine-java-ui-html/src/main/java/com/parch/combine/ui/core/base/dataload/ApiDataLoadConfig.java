package com.parch.combine.ui.core.base.dataload;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;

import java.util.List;
import java.util.Map;

/**
 * 数据加载配置
 */
@CommonObject(order = 2, name = "调用外部API数据源配置", desc = "当 TYPE = URL 时的参数列表")
public class ApiDataLoadConfig extends DataLoadConfig {

    @Field(key = "url", name = "请求地址", type = FieldTypeEnum.TEXT, isRequired = true)
    private String url;

    @Field(key = "mode", name = "请求方式 GET | POST", type = FieldTypeEnum.TEXT)
    private String mode;

    @Field(key = "params", name = "请求参数", type = FieldTypeEnum.MAP)
    private Object params;

    @Field(key = "params", name = "请求参数", type = FieldTypeEnum.MAP)
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

    public String getLocalStorageKey() {
        return localStorageKey;
    }

    public void setLocalStorageKey(String localStorageKey) {
        this.localStorageKey = localStorageKey;
    }
}
