package com.parch.combine.core.common.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface IMerge<T> {

    @SuppressWarnings("unchecked")
    default void merge(T source) throws IllegalAccessException {
        if (source == null) {
            return;
        }

        Class<?> sourceClass = source.getClass();
        if (!sourceClass.isAssignableFrom(this.getClass())) {
            return;
        }

        List<Field> fieldList = new ArrayList<>();
        Class<?> currClass = sourceClass;
        Field[] fieldArr;
        while (currClass != null && currClass != Object.class) {
            fieldArr = currClass.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fieldArr));
            currClass = currClass.getSuperclass();
        }

        for (Field field : fieldList) {
            field.setAccessible(true);

            Object sourceFieldValue = field.get(source);
            if (sourceFieldValue == null) {
                continue;
            }

            Object thisFieldValue = field.get(this);
            if (thisFieldValue == null) {
                field.set(this, sourceFieldValue);
                continue;
            }

            if (IMerge.class.isAssignableFrom(field.getType())) {
                ((IMerge) thisFieldValue).merge(sourceFieldValue);
            } else if (Map.class.isAssignableFrom(field.getType())) {
                ((Map) thisFieldValue).putAll((Map) thisFieldValue);
            } else {
                field.set(this, sourceFieldValue);
            }
        }
    };
}
