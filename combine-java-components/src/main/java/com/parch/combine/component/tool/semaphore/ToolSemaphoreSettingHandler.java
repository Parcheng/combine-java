package com.parch.combine.component.tool.semaphore;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class ToolSemaphoreSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("lock", "限流组件", true, ToolSemaphoreComponent.class);
        // builder.addDesc("流程逻辑限流");


        builder.addProperty(PropertyTypeEnum.INIT, "max", FieldTypeEnum.TEXT, "最大流量数配置",  false, false, "20");
        builder.addProperty(PropertyTypeEnum.INIT, "keyMaxes", FieldTypeEnum.OBJECT, "每个KEY的最大流量数配置",  false, false);

        builder.addPropertyEg(PropertyTypeEnum.INIT, "max", "100", "所有流程最大流量未 100");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "keyMaxes", "{\"user/findAll\": 1000, \"user/update\": 50,}",  "user/findAll 流程的最大允许流量未 1000，user/update 流程的最大允许流量未 50");


        builder.addProperty(PropertyTypeEnum.LOGIC, "key", FieldTypeEnum.TEXT, "限流KEY配置",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "key", "所有相同的KEY的流程的总流量数不能超过最大值，默认为流程KEY（单接口维度限流）");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "key", "user_change", "表示所有 KEY 为 user_change 的流程的流量总和不能超过20");


        builder.setResult("true 或报错", false);

        return builder.get();
    }
}
