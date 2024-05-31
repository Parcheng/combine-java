package com.parch.combine.core.component.a;

import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.component.handler.CombineManagerHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class LogicConfigProxy implements InvocationHandler, IInit {

    private String scopeKey;
    private Class<?> configClass;
    private Map<String, Object> config;

    public LogicConfigProxy(String scopeKey, Class<?> configClass, Map<String, Object> config) {
        this.scopeKey = scopeKey;
        this.configClass = configClass;
        this.config = config;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        Method[] methods = configClass.getMethods();
        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            if (field == null) {
                continue;
            }

            Class<?> typeClass = method.getReturnType();
            Object configFieldData = getConfigData(field.key());
            FieldTypeEnum[] type = field.type();
            switch (type[0]) {
                case ID:
                    if (configFieldData == null) {
                        setConfigData(field.key(), UUID.randomUUID().toString());
                    }
                    break;
                case OBJECT:
                    setConfigData(field.key(), ConfigProxyBuilder.builder(scopeKey, typeClass,
                            configFieldData instanceof Map ? (Map<String, Object>) configFieldData : null));
                    break;
                case ELEMENT:
                    CombineManager manager = CombineManagerHandler.get(scopeKey);
                    SubComponentTool.init(manager, configFieldData instanceof List ? (List<Object>) configFieldData : Collections.singletonList(configFieldData));
                    break;
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object defaultValue = null;
        if (method.isDefault()) {
            defaultValue = method.invoke(proxy, args);
        }

        Field field = method.getAnnotation(Field.class);
        if (field == null) {
            return defaultValue;
        }

        Object configFieldData = getConfigData(field.key());
        if (configFieldData == null) {
            return defaultValue;
        }

        configFieldData = DataVariableHelper.parse(configFieldData);
        Object result = null;
        try {
            Class<?> typeClass = method.getReturnType();
            if (typeClass.isInstance(configFieldData)) {
                result = configFieldData;
            } else if (String.class.equals(typeClass)) {
                result = DataParseUtil.getString(configFieldData);
            } else if (Double.class.equals(typeClass)) {
                result = Double.parseDouble(configFieldData.toString());
            } else if (Float.class.equals(typeClass)) {
                result = Float.parseFloat(configFieldData.toString());
            } else if (Long.class.equals(typeClass)) {
                result = Long.parseLong(configFieldData.toString());
            } else if (Integer.class.equals(typeClass)) {
                result = Integer.parseInt(configFieldData.toString());
            } else if (Short.class.equals(typeClass)) {
                result = Short.parseShort(configFieldData.toString());
            } else if (Date.class.equals(typeClass)) {
                result = DataParseUtil.parseDate(configFieldData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == null) {
            if (field.throwTypeError()) {
                throw new Exception("类型错误");
            } else {
                return defaultValue;
            }
        }

        return result;
    }

    private Object getConfigData(String fieldName) {
        if (config != null) {
            return config.get(fieldName);
        }
        return null;
    }

    private synchronized void setConfigData(String fieldName, Object data) {
        if (config == null) {
            config = new HashMap<>();
        }

        config.put(fieldName, data);
    }
}
