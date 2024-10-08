package com.parch.combine.html.common.cache.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.html.common.canstant.ConfigFiledConstant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheModelBuilder {

    private CacheModelBuilder() {}

    public static Map<String, Object> parseInterfaceToMap(String id, String type, Object interfaceObj) {
        Map<String, Object> config = parse(interfaceObj);
        config.put(ConfigFiledConstant.ID, id);
        config.put(ConfigFiledConstant.TYPE, type);
        return config;
    }

    private static Map<String, Object> parse(Object interfaceObj) {
        Map<String, Object> config = new HashMap<>();

        Method[] methods = interfaceObj.getClass().getMethods();
        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            if (field == null) {
                continue;
            }

            Object value = null;
            try {
                value = method.invoke(interfaceObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                PrintLogUtil.printError(e);
            }

            if (value != null && field.type() == FieldTypeEnum.CONFIG) {
                value = parse(value);
            }

            config.put(method.getName(), value);
        }

        return config;
    }
}
