package com.parch.combine.core.common.settings.annotations;

import com.parch.combine.core.common.settings.config.IOptionSetting;

import java.lang.annotation.*;

/**
 * 组的项的字段下拉
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldGroupSelect {
    /**
     * 项的索引
     */
    int index();

    /**
     * 下拉枚举Class
     */
    Class<? extends IOptionSetting> enumClass();
}
