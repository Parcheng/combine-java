package com.parch.combine.core.settings.annotations;

import com.parch.combine.core.settings.config.IOptionSetting;
import java.lang.annotation.*;

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
