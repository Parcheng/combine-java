package com.parch.combine.html.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

import java.util.Map;

public interface CallUrlTriggerLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "触发配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends TriggerConfig {

        @Field(key = "url", name = "URL地址", type = FieldTypeEnum.TEXT, isRequired = true)
        String url();

        @Field(key = "mode", name = "请求方式 POST | GET", type = FieldTypeEnum.TEXT, isRequired = true)
        String mode();

        @Field(key = "fromSubmit", name = "使用FROM表单方式提交", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean fromSubmit();

        @Field(key = "headers", name = "请求头", type = FieldTypeEnum.MAP)
        Map<String, String> params();

        @Field(key = "params", name = "请求参数", type = FieldTypeEnum.MAP)
        Map<String, String> headers();

        @Field(key = "localStorageKey", name = "浏览器缓存KEY", type = FieldTypeEnum.TEXT)
        @FieldDesc("以该值作为KEY将结果保存到浏览器缓存，不设置该值则不保存")
        String localStorageKey();
    }
}
