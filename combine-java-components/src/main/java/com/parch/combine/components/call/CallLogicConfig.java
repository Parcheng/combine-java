package com.parch.combine.components.call;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

public interface CallLogicConfig extends ILogicConfig {

    @Field(key = "url", name = "调用的地址", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc({"调用流程组件：该参数为流程KEY，如 user/save", "调用URL组件：该参数为URL地址，如 https://www.baidu.com"})
    String url();

    @Field(key = "params", name = "请求入参", type = FieldTypeEnum.MAP)
    @FieldDesc("注意：入参必须为对象结构（{ ... }）")
    @FieldEg(eg = "{ ... }", desc = "调用请求传入的参数")
    Map<String, Object> params();

    @Field(key = "headers", name = "请求头", type = FieldTypeEnum.MAP)
    Map<String, String> headers();
}
