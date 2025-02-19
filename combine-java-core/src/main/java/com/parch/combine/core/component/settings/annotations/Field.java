package com.parch.combine.core.component.settings.annotations;

import com.parch.combine.core.component.settings.config.FieldTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Field {
    /**
     * 字段KEY
     */
    String key();

    /**
     * 字段名
     */
    String name();

    /**
     * 顺序
     */
    float order() default 1;

    /**
     * 字段类型
     */
    FieldTypeEnum type();

    /**
     * 字段是否为数组
     */
    boolean isArray() default false;

    /**
     * 字段是否必填
     */
    boolean isRequired() default false;

    /**
     * 字段默认值
     */
    String defaultValue() default "";

    /**
     * 是否解析表达式
     */
    boolean parseExpression() default true;

    /**
     * 是否抛出类型转换错误
     */
    boolean throwTypeError() default true;
}
