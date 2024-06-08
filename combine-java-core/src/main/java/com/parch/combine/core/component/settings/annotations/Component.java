package com.parch.combine.core.component.settings.annotations;

import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.base.old.InitConfig;
import com.parch.combine.core.component.base.old.LogicConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
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
    Class<? extends ILogicConfig> logicConfigClass();

    /**
     * 初始化配置Class
     */
    Class<? extends IInitConfig> initConfigClass();
}
