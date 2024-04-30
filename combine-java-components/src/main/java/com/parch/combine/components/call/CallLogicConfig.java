package com.parch.combine.components.call;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.Map;

/**
 * 逻辑配置类
 */
public class CallLogicConfig extends LogicConfig {

    @ComponentField(key = "url", name = "调用的地址", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldDesc({"调用流程组件：该参数为流程KEY，如 user/save", "调用URL组件：该参数为URL地址，如 https://www.baidu.com"})
    private String url;

    @ComponentField(key = "params", name = "请求入参", type = FieldTypeEnum.OBJECT)
    @ComponentFieldDesc("注意：入参必须为对象结构（{ ... }）")
    @ComponentFieldEg(eg = "{ ... }", desc = "调用请求传入的参数")
    private Map<String, Object> params;

    @ComponentField(key = "headers", name = "请求头", type = FieldTypeEnum.OBJECT)
    private Map<String, String> headers;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
