package com.parch.combine.component.call.api;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class CallApiSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("api", "调用外部接口组件", false, CallApiComponent.class);
        // builder.addDesc("调用外部接口");

        builder.addProperty(PropertyTypeEnum.LOGIC, "mode", FieldTypeEnum.TEXT, "调用方式",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "mode", "可选值 POST | GET");
        builder.addProperty(PropertyTypeEnum.LOGIC, "url", FieldTypeEnum.TEXT, "请求地址",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "params", FieldTypeEnum.OBJECT, "请求参数",  false, false);

        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "mode", "GET", "以GET方式请求");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "url", "https://www.baidu.com", "请求百度网站");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "params", "{ ... }", "请求百度网站要传入的参数");

        builder.setResult("调用API返回的数据", false);
        return builder.get();
    }
}
