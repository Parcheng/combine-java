package com.parch.combine.core.common.settings.annotations;

import com.parch.combine.core.common.settings.config.IOptionSetting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段下拉
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface FieldSelect {
    /**
     * 下拉枚举Class
     */
    Class<? extends IOptionSetting> enumClass();

    /**
     * 是否验证下拉值
     */
    boolean isVerify() default true;
}
