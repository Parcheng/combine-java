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
public @interface ComponentCommonObject {
    /**
     * 顺序字段
     */
    int order() default 0;

    /**
     * KEY 用于属性引用（KEY可以重复）
     */
    String key();

    /**
     * KEY 用于属性引用
     */
    String name();

    /**
     * 详情
     */
    String[] desc() default {};
}
