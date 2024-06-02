package com.parch.combine.core.component.base.proxy;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
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

        T configProxy = (T) Proxy.newProxyInstance(ConfigProxy.class.getClassLoader(), new Class[]{configClass}, proxy);
        return new ThreeTuples<>(false, configProxy, null);
    }

    /**
     * 检查配置类是否合法
     */
    public static List<String> check(Class<?> config) {
        List<String> errorMsg = new ArrayList<>();

        Method[] methods = config.getMethods();
        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            if (field == null) {
                continue;
            }

            Class<?> typeClass = method.getReturnType();
            if (field.isArray()) {
                if (typeClass.isArray()) {
                    typeClass = typeClass.getComponentType();
                } else {
                    errorMsg.add(field.key() + "-被设置为数组，请使用数组类型");
                    continue;
                }
            }

            FieldTypeEnum type = field.type();
            switch (type) {
                case ID:
                    if (typeClass != String.class) {
                        errorMsg.add(field.key() + "-的类型配置定义与方法返回值不匹配，请使用字符串类型");
                    }
                    break;
                case BOOLEAN:
                    if (typeClass != Boolean.class) {
                        errorMsg.add(field.key() + "-的类型配置定义与方法返回值不匹配，请使用布尔类型");
                    }
                    break;
                case NUMBER:
                    if (!typeClass.isAssignableFrom(Number.class)) {
                        errorMsg.add(field.key() + "-的类型配置定义与方法返回值不匹配，请使用数字类型");
                    }
                    break;
                case SELECT:
                    if (typeClass != String.class) {
                        errorMsg.add(field.key() + "-的类型配置定义与方法返回值不匹配，请使用字符串类型");
                    }
                case MAP:
                    if (typeClass != Map.class) {
                        errorMsg.add(field.key() + "-的类型配置定义与方法返回值不匹配，请使用Map类型");
                    }
                    break;
                case CONFIG:
                    FieldObject fieldConfig = method.getAnnotation(FieldObject.class);
                    if (fieldConfig == null) {
                        errorMsg.add(field.key() + "-的类型配置定义缺少配置，请使用 FieldObject 注解指定配置类");
                    } else {
                        if (typeClass.isAssignableFrom(fieldConfig.value())) {
                            errorMsg.add(field.key() + "-的类型配置定义与 FieldObject 注解配置不一致");
                        }
                        List<String> subErrors = ConfigHandler.check(fieldConfig.value());
                        for (String subError : subErrors) {
                            errorMsg.add(field.key() + "-" + subError);
                        }
                    }
                    break;
                case OBJECT:
                    fieldConfig = method.getAnnotation(FieldObject.class);
                    if (fieldConfig == null) {
                        errorMsg.add(field.key() + "-的类型配置定义缺少配置，请使用 FieldObject 注解指定配置类");
                    }
                    break;
                case COMPONENT:
                    if (typeClass != List.class) {
                        errorMsg.add(field.key() + "-的类型配置定义与方法返回值不匹配，请使用字符串 List 类型");
                    }
                    break;
                case EXPRESSION:
                    if (!field.parseExpression()) {
                        errorMsg.add(field.key() + "-表达式类型的字段, Field 注解的 parseExpression 配置必须为 true");
                    }
                case ANY:
                    if (typeClass != Object.class) {
                        errorMsg.add(field.key() + "-的类型配置定义与方法返回值不匹配，请使用 Object 类型");
                    }
                    break;
                default:
                    errorMsg.add(field.key() + "-不支持该类型配置定义");
                    break;
            }
        }

        return errorMsg;
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
        Object[] resultData = new Object[listData.size()];
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < listData.size(); i++) {
            Object item = listData.get(i);
            Object itemData = null;
            switch (type) {
                case ID:
                case TEXT:
                case SELECT:
                    // TODO 检查类型
                    itemData = DataParseUtil.getString(item);
                    break;
                case BOOLEAN:
                    itemData = DataParseUtil.parseBoolean(item);
                    break;
                case NUMBER:
                    itemData = DataParseUtil.parseNumber(item);
                    break;
                case MAP:
                    itemData = item instanceof Map ? item : null;
                    break;
                case CONFIG:
                    FieldObject fieldConfig = method.getAnnotation(FieldObject.class);
                    if (item instanceof Map && fieldConfig != null){
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
                    if (item instanceof Map && fieldConfig != null) {
                        // TODO 手动序列化，补校验逻辑
                        try {
                            itemData = JsonUtil.parseArray(JsonUtil.serialize(item), fieldConfig.value());
                        } catch (Exception e) {
                            errors.add("JSON序列号异常-" + e.getMessage());
                        }
                    }
                case COMPONENT:
                    CombineManager manager = CombineManagerHandler.get(scopeKey);
                    if (item instanceof Map) {
                        CombineInitVO initVO = manager.getComponent().init(Collections.singletonList((Map<String, Object>) item));
                        if (initVO.isSuccess()) {
                            itemData = CheckEmptyUtil.isNotEmpty(initVO.getComponentIds()) ? initVO.getComponentIds().get(0) : initVO.getStaticComponentIds().get(0);
                        } else {
                            errors.addAll(initVO.getErrorList());
                        }
                    } else {
                        AbsComponent<?,?> component = manager.getComponent().getComponent(item.toString());
                        if (component == null) {
                            errors.add("ID【" + item.toString() + "】的组件不存在");
                        } else {
                            itemData = item.toString();
                        }
                    }
                    break;
                case EXPRESSION:
                case ANY:
                    itemData = item;
                    break;
            }

            if (itemData == null) {
                errors.add((isArray ? ("第" + (i + 1) + "项") : "") + data.getClass() + "数据与类型" + type.name() + "不匹配");
            } else {
                resultData[i] = itemData;
            }
        }

        return new ThreeTuples<>(CheckEmptyUtil.isNotEmpty(errors), isArray ? resultData : resultData[0], errors);
    }
}
