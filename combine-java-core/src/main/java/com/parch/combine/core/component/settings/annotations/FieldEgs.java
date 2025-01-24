package com.parch.combine.core.component.settings.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface FieldEgs {
    FieldEg[] value();
}
