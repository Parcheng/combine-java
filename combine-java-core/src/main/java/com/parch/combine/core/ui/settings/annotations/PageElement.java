package com.parch.combine.core.ui.settings.annotations;

import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.ui.base.ElementLogicConfig;
import com.parch.combine.core.ui.base.ElementTemplateConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PageElement {
    /**
     * 顺序
     */
    int order() default 0;

    /**
     * 组件KEY
     */
    String key();

    /**
     * 组件名
     */
    String name();

    /**
     * 组件描述
     */
    String desc() default "";

    /**
     * 逻辑配置Class
     */
    Class<? extends ElementLogicConfig> logicConfigClass();

    /**
     * 初始化配置Class
     */
    Class<? extends ElementTemplateConfig> templateConfigClass();
}
