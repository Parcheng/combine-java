package com.parch.combine.ui.core.settings.annotations;

import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
     * 模板Class
     */
    Class<? extends ElementTemplateConfig> templateClass();
}
