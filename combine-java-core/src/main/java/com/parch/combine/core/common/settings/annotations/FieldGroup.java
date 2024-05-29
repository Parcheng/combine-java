package com.parch.combine.core.common.settings.annotations;

import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.lang.annotation.*;

/**
 * 组件字段组的项
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(FieldGroups.class)
public @interface FieldGroup {
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
    FieldTypeEnum[] type();

    /**
     * 项是否必须
     */
    boolean isRequired() default true;
}
