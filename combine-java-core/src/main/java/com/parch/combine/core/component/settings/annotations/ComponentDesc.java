package com.parch.combine.core.component.settings.annotations;

import java.lang.annotation.*;

/**
 * 组件描述注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentDesc {
    /**
     * 描述行集合
     */
    String[] value();
}
