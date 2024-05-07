package com.parch.combine.core.common.settings.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldEgs {
    FieldEg[] value();
}
