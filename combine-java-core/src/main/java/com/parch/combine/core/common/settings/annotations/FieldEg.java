package com.parch.combine.core.common.settings.annotations;

import java.lang.annotation.*;

/**
 * 组件字段示例
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(FieldEgs.class)
public @interface FieldEg {
    /**
     * 示例
     */
    String eg();

    /**
     * 示例描述
     */
    String desc() default "";
}
