package com.parch.combine.ui.settings.annotations;

import java.lang.annotation.*;

/**
 * 组件字段示例
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(ComponentFieldEgs.class)
public @interface ComponentFieldEg {
    /**
     * 示例
     */
    String eg();

    /**
     * 示例描述
     */
    String desc() default "";
}
