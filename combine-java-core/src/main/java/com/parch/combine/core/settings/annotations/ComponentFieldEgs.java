package com.parch.combine.core.settings.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ComponentFieldEgs {
    ComponentFieldEg[] value();
}
