package com.parch.combine.ui.settings.annotations;

import com.parch.combine.core.common.settings.config.IOptionSetting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组的项的字段下拉
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ComponentFieldGroupSelect {
    /**
     * 项的索引
     */
    int index();

    /**
     * 下拉枚举Class
     */
    Class<? extends IOptionSetting> enumClass();
}
