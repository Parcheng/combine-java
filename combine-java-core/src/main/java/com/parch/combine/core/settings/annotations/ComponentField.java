package com.parch.combine.core.settings.annotations;

import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ComponentField {
    /**
     * 字段KEY
     */
    String key();

    /**
     * 字段名
     */
    String name();

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
}
