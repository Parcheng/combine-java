package com.parch.combine.core.common.settings.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface FieldObject {

    Class<?> value();
}
