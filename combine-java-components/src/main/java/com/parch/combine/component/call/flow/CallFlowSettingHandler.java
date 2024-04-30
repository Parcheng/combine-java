package com.parch.combine.component.call.flow;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class CallFlowSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("flow", "调用内部流程组件", false, CallFlowComponent.class);
        // builder.addDesc("调用内容流程");

        builder.addProperty(PropertyTypeEnum.LOGIC, "url", FieldTypeEnum.TEXT, "调用的流程KEY",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "params", FieldTypeEnum.OBJECT, "调用流程的入参",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "params", "注意：入参必须为对象结构（{ ... }）");

        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "url", "user/save", "请求内部Key为user/save流程");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "params", "{ ... }", "请求内部流程传入的参数");

        builder.setResult("调用流程的返回结果", false);
        return builder.get();
    }
}
