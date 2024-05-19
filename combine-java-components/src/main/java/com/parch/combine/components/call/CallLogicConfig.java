package com.parch.combine.components.call;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

/**
 * 逻辑配置类
 */
public class CallLogicConfig extends LogicConfig {

    @Field(key = "url", name = "调用的地址", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc({"调用流程组件：该参数为流程KEY，如 user/save", "调用URL组件：该参数为URL地址，如 https://www.baidu.com"})
    private String url;

    @Field(key = "params", name = "请求入参", type = FieldTypeEnum.OBJECT)
    @FieldDesc("注意：入参必须为对象结构（{ ... }）")
    @FieldEg(eg = "{ ... }", desc = "调用请求传入的参数")
    private Map<String, Object> params;

    @Field(key = "headers", name = "请求头", type = FieldTypeEnum.OBJECT)
    private Map<String, String> headers;

    @Override
    public void init() {}

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
