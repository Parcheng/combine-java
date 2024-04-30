package com.parch.combine.core.settings.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件结果
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentResult {
    /**
     * 结果信息
     */
    String name();

    /**
     * 是否下载
     */
    boolean isDownload() default false;
}
