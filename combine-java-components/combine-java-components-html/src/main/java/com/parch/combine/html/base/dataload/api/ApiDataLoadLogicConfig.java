package com.parch.combine.html.base.dataload.api;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.dataload.DataLoadConfig;

import java.util.Map;

public interface ApiDataLoadLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "数据加载配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends DataLoadConfig {

        @Field(key = "url", name = "请求地址", type = FieldTypeEnum.TEXT, isRequired = true)
        String url();

        @Field(key = "mode", name = "请求方式 GET | POST", type = FieldTypeEnum.TEXT)
        String mode();

        @Field(key = "params", name = "请求参数", type = FieldTypeEnum.MAP)
        Object params();

        @Field(key = "params", name = "请求参数", type = FieldTypeEnum.MAP)
        Map<String, String> headers();

        @Field(key = "localStorageKey", name = "浏览器缓存KEY", type = FieldTypeEnum.TEXT)
        @FieldDesc("以该值作为KEY将结果保存到浏览器缓存，不设置该值则不保存")
        String localStorageKey();
    }
}
