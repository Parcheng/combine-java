package com.parch.combine.core.component.base.proxy;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.util.tuple.ThreeTuples;
import com.parch.combine.core.component.error.ComponentErrorHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class ConfigProxy implements InvocationHandler {

    private String scopeKey;
    private Class<?> configClass;
    private Map<String, Object> config;
    private Map<String, Boolean> configFlagMap = new HashMap<>(16);

    public ConfigProxy(String scopeKey, Class<?> configClass, Map<String, Object> config) {
        this.scopeKey = scopeKey;
        this.configClass = configClass;
        this.config = config == null ? new HashMap<>(1) : null;
    }

    public List<String> init() {
        List<String> errors = new ArrayList<>();
        Method[] methods = configClass.getMethods();
        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            if (field == null) {
                continue;
            }

            String key = field.key();

            // 字段必填验证
            Object configFieldData = config.get(key);
            if (field.isRequired() && configFieldData == null) {
                errors.add(key + "字段不能为空");
                continue;
            }

            // 数据为空将标识设置成空
            if (configFieldData == null) {
                configFlagMap.put(key, null);
                continue;
            }

            // 允许使用表达式，并且数据中包含表达式，标识设置为false（实时解析）
            if (field.parseExpression() && ConfigHandler.fieldDataHasExpression(field.type(), configFieldData, field.isArray())) {
                configFlagMap.put(key, true);
                continue;
            }

            ThreeTuples<Boolean, Object, List<String>> parseResult = ConfigHandler.buildFieldData(scopeKey, field.type(), configFieldData, field.isArray(), method);
            if (parseResult.getFirst()) {
                config.put(key, parseResult.getSecond());
                configFlagMap.put(key, false);
            } else {
                for (String item : parseResult.getThird()) {
                    errors.add(field.key() + "-" + item);
                }
            }
        }

        return errors;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object defaultValue = null;
        if (method.isDefault()) {
            defaultValue = method.invoke(proxy, args);
        }

        Field field = method.getAnnotation(Field.class);
        if (field == null) {
            return defaultValue;
        }

        Boolean parseExpression = configFlagMap.get(field.key());
        if (parseExpression == null) {
            return defaultValue;
        }

        Object configFieldData = config.get(field.key());
        if (!parseExpression) {
            return configFieldData == null ? defaultValue : configFieldData;
        } else {
            configFieldData = ConfigHandler.parseFieldData(field.type(), configFieldData, field.isArray());
        }

        if (field.isRequired() && configFieldData == null) {
            String msg = field.key() + "-字段不能为空";
            ComponentErrorHandler.print(msg);
            throw new Exception(msg);
        }

        if (configFieldData != null) {
            ThreeTuples<Boolean, Object, List<String>> parseResult = ConfigHandler.buildFieldData(scopeKey, field.type(), configFieldData, field.isArray(), method);
            if (parseResult.getFirst()) {
                configFieldData = parseResult.getSecond();
            } else {
                if (field.throwTypeError()) {
                    for (String msg : parseResult.getThird()) {
                        ComponentErrorHandler.print(field.key() + "-" + msg);
                    }
                    throw new Exception(field.key() + "-动态加载配置数据失败");
                }
            }
        }

        return configFieldData;
    }

}
