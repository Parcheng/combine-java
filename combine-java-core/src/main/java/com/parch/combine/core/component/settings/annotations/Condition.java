package com.parch.combine.core.component.settings.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 条件配置注解（暂未使用）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Condition {

    /**
     * key
     */
    String key();

    /**
     * 值
     */
    String value();
}
