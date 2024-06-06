package com.parch.combine.core.component.base.proxy;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.tuple.ThreeTuples;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.handler.CombineManagerHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.variable.DataVariableFlagHelper;

import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.CombineInitVO;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class ConfigHandler {

    /**
     * 构建配置类
     */
    @SuppressWarnings("unchecked")
    public static <T> ThreeTuples<Boolean, T, List<String>> build(String scopeKey, Class<T> configClass, Map<String, Object> config) {
        ConfigProxy proxy = new ConfigProxy(scopeKey, configClass, config);

        List<String> errors = proxy.init();
        if (CheckEmptyUtil.isNotEmpty(errors)) {
            return new ThreeTuples<>(false, null, errors);
        }

        T configProxy = (T) Proxy.newProxyInstance(configClass.getClassLoader(), new Class[]{configClass}, proxy);
        return new ThreeTuples<>(true, configProxy, null);
    }

    /**
     * 检查配置类是否合法
     */
    public static List<String> check(Class<?> config) {
        List<String> errorMsg = new ArrayList<>();
        Class<?> superclass = config.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            errorMsg.addAll(check(superclass));
        }

//        if (!config.isInterface()) {
//            java.lang.reflect.Field[] fields = config.getFields();
//            for (AnnotatedElement item : fields) {
//                CheckField(item, errorMsg);
//            }
//        }

        Method[] methods = config.getMethods();
        for (AnnotatedElement item : methods) {
            CheckField(item, errorMsg);
        }

        return errorMsg;
    }

    private static void CheckField(AnnotatedElement item, List<String> errorMsg) {
        Field field = item.getAnnotation(Field.class);
        if (field == null) {
            return;
        }

        Class<?> typeClass = item instanceof Method ? ((Method) item).getReturnType() : ((java.lang.reflect.Field) item).getType();
        if (field.isArray()) {
            if (typeClass.isArray()) {
                typeClass = typeClass.getComponentType();
            } else {
                errorMsg.add(field.key() + " > 被设置为数组，请使用数组类型");
                return;
            }
        }

        FieldTypeEnum type = field.type();
        switch (type) {
            case ID:
            case TEXT:
            case COMPONENT:
                if (typeClass != String.class) {
                    errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，请使用字符串类型");
                }
                break;
            case BOOLEAN:
                if (typeClass != Boolean.class) {
                    errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，请使用布尔类型");
                }
                break;
            case NUMBER:
                if (!Number.class.isAssignableFrom(typeClass)) {
                    errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，请使用数字类型");
                }
                break;
            case SELECT:
                FieldSelect fieldSelect = item.getAnnotation(FieldSelect.class);
                if (fieldSelect == null) {
                    errorMsg.add(field.key() + " > 的类型配置定义缺少配置，请使用 FieldSelect 注解指定配置类");
                }
                if (typeClass != String.class) {
                    errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，请使用字符串类型");
                }
                break;
            case MAP:
                if (typeClass != Map.class) {
                    errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，请使用Map类型");
                }
                break;
            case CONFIG:
                FieldObject fieldConfig = item.getAnnotation(FieldObject.class);
                if (fieldConfig == null) {
                    errorMsg.add(field.key() + " > 的类型配置定义缺少配置，请使用 FieldObject 注解指定配置类");
                } else {
                    if (!fieldConfig.value().isAssignableFrom(typeClass)) {
                        errorMsg.add(field.key() + " > 的类型配置定义与 FieldObject 注解配置不一致");
                    }
                    List<String> subErrors = ConfigHandler.check(fieldConfig.value());
                    for (String subError : subErrors) {
                        errorMsg.add(field.key() + "." + subError);
                    }
                }
                break;
            case OBJECT:
                fieldConfig = item.getAnnotation(FieldObject.class);
                if (fieldConfig == null) {
                    errorMsg.add(field.key() + " > 的类型配置定义缺少配置，请使用 FieldObject 注解指定配置类");
                }
                break;
            case EXPRESSION:
                if (field.parseExpression()) {
                    if (typeClass != Object.class) {
                        errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，请使用 Object 类型");
                    }
                } else {
                    if (typeClass != String.class) {
                        errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，不自动解析表达式时，请使用字符串类型");
                    }
                }
                break;
            case ANY:
                if (typeClass != Object.class) {
                    errorMsg.add(field.key() + " > 的类型配置定义与方法返回值不匹配，请使用 Object 类型");
                }
                break;
            default:
                errorMsg.add(field.key() + " > 不支持该类型配置定义");
                break;
        }
    }

    /**
     * 配置字段的数据是否包含表达式
     */
    @SuppressWarnings("unchecked")
    public static boolean fieldDataHasExpression(FieldTypeEnum type, Object data, boolean isArray) {
        List<Object> listData = isArray && data instanceof List ? (List<Object>) data : Collections.singletonList(data);

        boolean hasExpression = false;
        for (Object item : listData) {
            switch (type) {
                case ID:
                case TEXT:
                case SELECT:
                case BOOLEAN:
                case NUMBER:
                case CONFIG:
                case OBJECT:
                case COMPONENT:
                    hasExpression = DataTypeIsUtil.isString(item) && DataVariableFlagHelper.hasParseFlag(item.toString());
                    break;
                case EXPRESSION:
                    hasExpression = true;
                    break;
                case MAP:
                    hasExpression = (DataTypeIsUtil.isString(item) || item instanceof Map) && DataVariableFlagHelper.hasParseFlag(item);
                    break;
                case ANY:
                    hasExpression = DataVariableFlagHelper.hasParseFlag(item);
                    break;
            }

            if (hasExpression) {
                return hasExpression;
            }
        }

        return false;
    }

    /**
     * 解析字段数据
     */
    @SuppressWarnings("unchecked")
    public static Object parseFieldData(FieldTypeEnum type, Object data, boolean isArray) {
        boolean dataIsArray = isArray && data instanceof List;
        List<Object> listData = dataIsArray ? (List<Object>) data : Collections.singletonList(data);

        List<Object> finalData = new ArrayList<>();
        for (Object item : listData) {
            switch (type) {
                case ID:
                case TEXT:
                case SELECT:
                case BOOLEAN:
                case NUMBER:
                case CONFIG:
                case OBJECT:
                case COMPONENT:
                    finalData.add(DataVariableHelper.parseValue(item, false));
                    break;
                case EXPRESSION:
                    finalData.add(DataVariableHelper.parseValue(item, true));
                    break;
                case MAP:
                case ANY:
                default:
                    finalData.add(DataVariableHelper.parse(item));
                    break;
            }
        }

        return dataIsArray ? finalData : finalData.get(0);
    }

    /**
     * 构建字段数据
     */
    @SuppressWarnings("unchecked")
    public static ThreeTuples<Boolean, Object, List<String>> buildFieldData(String scopeKey, FieldTypeEnum type, Object data, boolean isArray, Method method) {
        List<Object> listData = isArray && data instanceof List ? (List<Object>) data : Collections.singletonList(data);
        Class<?> returnType = isArray ? method.getReturnType().getComponentType() : method.getReturnType();

        List<String> errors = new ArrayList<>();
        Object arrayData = Array.newInstance(returnType, listData.size());
        for (int i = 0; i < listData.size(); i++) {
            Object item = listData.get(i);
            // 当数据为集合时，集合的某一项为动态取值，可能存在为空的情况
            if (item == null) {
                continue;
            }

            Object itemData = null;
            switch (type) {
                case ID:
                case TEXT:
                case SELECT:
                    itemData = DataParseUtil.getString(item);
                    break;
                case BOOLEAN:
                    itemData = DataParseUtil.parseBoolean(item);
                    break;
                case NUMBER:
                    itemData = DataParseUtil.parseNumber(item, returnType);
                    break;
                case MAP:
                    itemData = item instanceof Map ? item : null;
                    break;
                case CONFIG:
                    FieldObject fieldConfig = method.getAnnotation(FieldObject.class);
                    if (item instanceof Map && fieldConfig != null && returnType == fieldConfig.value()){
                        ThreeTuples<Boolean, ?, List<String>> buildResult = build(scopeKey, fieldConfig.value(), (Map<String, Object>) item);
                        if (buildResult.getFirst()) {
                            itemData = buildResult.getSecond();
                        } else {
                            for (String error : buildResult.getThird()) {
                                errors.add("初始化异常-" + error);
                            }
                        }
                    }
                    break;
                case OBJECT:
                    fieldConfig = method.getAnnotation(FieldObject.class);
                    if (item instanceof Map && fieldConfig != null && returnType == fieldConfig.value()) {
                        try {
                            itemData = JsonUtil.deserialize(JsonUtil.serialize(item), fieldConfig.value());
                        } catch (Exception e) {
                            errors.add("JSON序列号异常-" + e.getMessage());
                        }
                    }
                    break;
                case COMPONENT:
                    CombineManager manager = CombineManagerHandler.get(scopeKey);
                    if (item instanceof Map) {
                        CombineInitVO initVO = manager.getComponent().init(Collections.singletonList((Map<String, Object>) item));
                        if (initVO.isSuccess()) {
                            itemData = CheckEmptyUtil.isNotEmpty(initVO.getComponentIds()) ? initVO.getComponentIds().get(0) : null;
                        } else {
                            errors.addAll(initVO.getErrorList());
                            itemData = CheckEmptyUtil.EMPTY;
                        }
                    } else {
                        AbsComponent<?,?> component = manager.getComponent().getComponent(item.toString());
                        if (component == null) {
                            errors.add("ID[" + item.toString() + "]的组件不存在");
                        }
                        itemData = item.toString();
                    }
                    break;
                case EXPRESSION:
                case ANY:
                    itemData = item;
                    break;
            }

            if (itemData == null) {
                errors.add((isArray ? ("第" + (i + 1) + "项-[") : "-[") + item.getClass().getName() + "]数据与类型[" + type.name() + "]不匹配");
            }

            Array.set(arrayData, i, returnType.cast(itemData));
        }

        return new ThreeTuples<>(CheckEmptyUtil.isEmpty(errors), isArray ? arrayData : Array.get(arrayData, 0), errors);
    }
}
