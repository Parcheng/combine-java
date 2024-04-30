package com.parch.combine.components.web.elements.dataload;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.Map;

/**
 * 数据加载配置
 */
@ComponentCommonObject(order = 2, key = WebSettingCanstant.DATA_LOAD_KEY, name = "加载流程数据源配置", desc = "当 TYPE = FLOW 时的参数列表")
public class FlowDataLoadEntity extends DataLoadEntity {

    @ComponentField(key = "flow", name = "流程KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String flow;

    @ComponentField(key = "params", name = "流程参数", type = FieldTypeEnum.OBJECT)
    private Object params;

    @ComponentField(key = "params", name = "请求参数", type = FieldTypeEnum.OBJECT)
    private Map<String, String> headers;

    @ComponentField(key = "localStorageKey", name = "浏览器缓存KEY", type = FieldTypeEnum.TEXT)
    @ComponentFieldDesc("以该值作为KEY将结果保存到浏览器缓存，不设置该值则不保存")
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
