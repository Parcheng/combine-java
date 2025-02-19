package com.parch.combine.core.component.tools.config;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.tuple.ThreeTuples;
import com.parch.combine.core.component.tools.PrintErrorHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigProxy implements InvocationHandler {

    private final String scopeKey;
    private final Class<?> configClass;
    private final Map<String, Object> config;
    private final Map<String, Boolean> configFlagMap = new HashMap<>(16);

    public ConfigProxy(String scopeKey, Class<?> configClass, Map<String, Object> config) {
        this.scopeKey = scopeKey;
        this.configClass = configClass;
        this.config = config == null ? new HashMap<>(1) : config;
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

            // 空数据处理
            Object configFieldData = config.get(key);
            if (configFieldData == null && CheckEmptyUtil.isNotEmpty(field.defaultValue())) {
                configFieldData = field.defaultValue();
                config.put(key, configFieldData);
            }

            // 空数据处理
            if (configFieldData == null) {
                // 字段必填验证
                if (field.isRequired()) {
                    errors.add(PrintErrorHelper.buildFieldMsg(key, "字段不能为空"));
                }

                // 设置数据为空标识
                configFlagMap.put(key, null);
                continue;
            }

            // 允许使用表达式，并且数据中包含表达式，标识设置为false（获取时实时解析）
            if (field.parseExpression() && ConfigHelper.fieldDataHasExpression(field.type(), configFieldData, field.isArray())) {
                configFlagMap.put(key, true);
                continue;
            }

            // 解析数据
            ThreeTuples<Boolean, Object, List<String>> parseResult = ConfigHelper.buildFieldData(scopeKey, field.type(), configFieldData, field.isArray(), method);
            if (parseResult.getFirst()) {
                config.put(key, parseResult.getSecond());
                configFlagMap.put(key, false);
            } else {
                for (String item : parseResult.getThird()) {
                    errors.add(PrintErrorHelper.buildFieldMsg(field.key(), item));
                }
            }
        }

        return errors;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Field field = method.getAnnotation(Field.class);
        if (field == null) {
            if ("toString".equals(method.getName())) {
                return configClass.getName();
            }

            return null;
        }

        String key = field.key();
        Boolean parseExpression = configFlagMap.get(key);
        if (parseExpression == null) {
            return null;
        }

        Object configFieldData = config.get(key);
        if (!parseExpression) {
            return configFieldData;
        }

        configFieldData = ConfigHelper.parseFieldData(field.type(), configFieldData, field.isArray());
        if (field.isRequired() && configFieldData == null) {
            String msg = PrintErrorHelper.buildFieldMsg(key, "字段不能为空");
            PrintErrorHelper.print(msg);
            throw new Exception(msg);
        }

        if (configFieldData != null) {
            ThreeTuples<Boolean, Object, List<String>> parseResult = ConfigHelper.buildFieldData(scopeKey, field.type(), configFieldData, field.isArray(), method);
            if (parseResult.getFirst()) {
                configFieldData = parseResult.getSecond();
            } else {
                for (String msg : parseResult.getThird()) {
                    PrintErrorHelper.print(PrintErrorHelper.buildFieldMsg(key, msg));
                }
                if (field.throwTypeError()) {
                    throw new Exception(PrintErrorHelper.buildFieldMsg(key, "动态加载配置数据失败"));
                }
            }
        }

        return configFieldData;
    }
}
