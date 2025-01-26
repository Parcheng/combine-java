package com.parch.combine.core.component.settings.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件公共对象注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommonObject {
    /**
     * 顺序字段
     */
    int order() default 0;

    /**
     * name 名称
     */
    String name();

    /**
     * 详情
     */
    String[] desc() default {};
}
