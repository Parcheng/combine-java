package com.parch.combine.core.component.settings.builder;

import com.parch.combine.core.common.settings.annotations.Invalid;
import com.parch.combine.core.common.settings.builder.CommonObjectSettingBuilder;
import com.parch.combine.core.common.settings.builder.PropertySettingBuilder;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.settings.annotations.*;
import com.parch.combine.core.component.settings.config.ComponentResultSetting;
import com.parch.combine.core.component.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.Arrays;

public class ComponentSettingBuilder {

    @SuppressWarnings("unchecked")
    public static ComponentSetting build(String scope, Class<?> componentClass) {
        Component componentAnnotation = componentClass.getAnnotation(Component.class);
        if (componentAnnotation == null || componentClass.getAnnotation(Invalid.class) != null) {
            return null;
        }

        // 组件
        ComponentSetting componentSetting = new ComponentSetting();
        componentSetting.setOrder(componentAnnotation.order());
        componentSetting.setKey(scope + "." + componentAnnotation.key());
        componentSetting.setName(componentAnnotation.name());
        componentSetting.setDesc(new ArrayList<>());
        componentSetting.setComponentClass((Class<? extends AbstractComponent<?, ?>>) componentClass);
        ComponentDesc componentDescAnnotation = componentClass.getAnnotation(ComponentDesc.class);
        if (componentDescAnnotation != null) {
            componentSetting.getDesc().addAll(Arrays.asList(componentDescAnnotation.value()));
        }

        // 逻辑配置
        componentSetting.setLogicConfig(PropertySettingBuilder.build(scope, componentAnnotation.logicConfigClass()));

        // 初始化配置
        Class<? extends IInitConfig> initConfigClass = componentAnnotation.initConfigClass();
        if (initConfigClass.getAnnotation(Invalid.class) == null) {
            componentSetting.setInitConfig(PropertySettingBuilder.build(scope, initConfigClass));
        }

        // 组件结果
        setResult(componentSetting, componentClass);
        return componentSetting;
    }

    private static void setResult(ComponentSetting componentSetting, Class<?> serviceClass) {
        // 组件结果
        ComponentResult componentResultAnnotation = serviceClass.getAnnotation(ComponentResult.class);
        if (componentResultAnnotation != null) {
            ComponentResultSetting componentResultSetting = new ComponentResultSetting();
            componentResultSetting.setInfo(componentResultAnnotation.name());
            componentResultSetting.setIsDownload(componentResultAnnotation.isDownload());

            ComponentResultDesc componentResultDescAnnotation = serviceClass.getAnnotation(ComponentResultDesc.class);
            if (componentResultDescAnnotation != null) {
                componentResultSetting.setDesc(Arrays.asList(componentResultDescAnnotation.value()));
            }

            componentSetting.setResult(componentResultSetting);
        }
    }
}
