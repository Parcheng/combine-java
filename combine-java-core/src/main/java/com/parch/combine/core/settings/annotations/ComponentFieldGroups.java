package com.parch.combine.core.settings.annotations;

import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ComponentFieldGroups {
    ComponentFieldGroup[] value();
}
