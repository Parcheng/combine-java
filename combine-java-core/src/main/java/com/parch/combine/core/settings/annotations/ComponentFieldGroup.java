package com.parch.combine.core.settings.annotations;

import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.lang.annotation.*;

/**
 * 组件字段组的项
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(ComponentFieldGroups.class)
public @interface ComponentFieldGroup {
    /**
     * 项索引
     */
    int index();

    /**
     * 项名称
     */
    String name();

    /**
     * 项类型
     */
    FieldTypeEnum type();

    /**
     * 项是否必须
     */
    boolean isRequired() default true;
}
