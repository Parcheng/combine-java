package com.parch.combine.core.common.settings.builder;

import com.parch.combine.core.common.settings.config.*;
import com.parch.combine.core.common.settings.helper.FieldSelectHelper;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.annotations.*;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;

public class PropertySettingBuilder {

    public static List<PropertySetting> build(String scope, Class<?> propertyClass) {
        // 构建属性集合
        Set<Class<?>> parsedClass = new HashSet<>();
        List<PropertySetting> properties = new ArrayList<>();
        buildProperties(scope, properties, propertyClass, CheckEmptyUtil.EMPTY, parsedClass);

        // 清理重复
        Set<String> keySet = new HashSet<>();
        for (int i = properties.size() - 1; i >= 0; i--) {
            PropertySetting obj = properties.get(i);
            if (keySet.contains(obj.getKey())) {
                properties.remove(i);
            } else {
                keySet.add(obj.getKey());
            }
        }

        properties.sort((o1, o2) -> Float.compare(o1.getOrder(), o2.getOrder()));
        return properties;
    }

    private static void buildProperties(String scope, List<PropertySetting> properties, Class<?> propertyClass, String keyPrefix, Set<Class<?>> parsedClass) {
        // 防循环引用
        if (parsedClass.contains(propertyClass)) {
            return;
        }
        parsedClass.add(propertyClass);

        // 解析父类
        Class<?> superclass = propertyClass.getSuperclass();
        if (superclass != null && superclass != Object.class) {
//            boolean success = CommonObjectSettingBuilder.load(scope, propertyClass);
//            if (success) {
//                // 继承公共对象的情况
//                CommonObjectSetting commonSetting = CommonObjectSettingBuilder.get(scope, propertyClass.getName());
//                PropertySetting commonPropertyRef = new PropertySetting();
//                commonPropertyRef.setKey("提示");
//                commonPropertyRef.setName("本配置对象支持【" + commonSetting.getName() + "】的全部配置项");
//                commonPropertyRef.setDesc(Collections.singletonList("配置项列表，请参考公共对象【" + commonSetting.getName() + "】"));
//                properties.add(commonPropertyRef);
//            } else {
            buildProperties(scope, properties, superclass, keyPrefix, parsedClass);
//            }
        }

        // 解析字段
        java.lang.reflect.Field[] fieldArr = propertyClass.getDeclaredFields();
        for (java.lang.reflect.Field field : fieldArr) {
            field.setAccessible(true);
            buildProperty(scope, properties, field, keyPrefix, parsedClass);
        }

        // 解析方法 getDeclaredMethods
        Method[] methodArr = propertyClass.getMethods();
        for (Method method : methodArr) {
            buildProperty(scope, properties, method, keyPrefix, parsedClass);
        }
    }

    private static void buildProperty(String scope, List<PropertySetting> properties, AnnotatedElement field, String keyPrefix,  Set<Class<?>> parsedClass) {
        PropertySetting property = setProperty(properties, field, keyPrefix);
        if (property == null) {
            return ;
        }

        FieldTypeEnum type = property.getType();
        switch (type) {
            case CONFIG:
            case OBJECT:
                setConfigProperty(scope, properties, property, field, keyPrefix, parsedClass);
                break;
            case SELECT:
                setSelect(property, field);
                break;
            default:
                break;
        }

        setEgs(property, field);
    }

    private static PropertySetting setProperty(List<PropertySetting> properties, AnnotatedElement field, String keyPrefix) {
        Field fieldAnnotation = field.getAnnotation(Field.class);
        if (fieldAnnotation == null || field.getAnnotation(Invalid.class) != null) {
            return null;
        }

        PropertySetting property = new PropertySetting();
        property.setKey(keyPrefix + fieldAnnotation.key());
        property.setName(fieldAnnotation.name());
        property.setType(fieldAnnotation.type());
        property.setOrder(fieldAnnotation.order());
        property.setIsRequired(fieldAnnotation.isRequired());
        property.setIsArray(fieldAnnotation.isArray());
        property.setDesc(new ArrayList<>());
        property.setDefaultValue(CheckEmptyUtil.isEmpty(fieldAnnotation.defaultValue()) ? null : fieldAnnotation.defaultValue());

        FieldDesc fieldDescAnnotation = field.getAnnotation(FieldDesc.class);
        if (fieldDescAnnotation != null) {
            property.getDesc().addAll(Arrays.asList(fieldDescAnnotation.value()));
        }

        properties.add(property);
        return property;
    }

    private static void setConfigProperty(String scope, List<PropertySetting> properties, PropertySetting property, AnnotatedElement field, String keyPrefix, Set<Class<?>> parsedClass) {
        FieldRef fieldRefAnnotation = field.getAnnotation(FieldRef.class);
        if (fieldRefAnnotation != null) {
            // 加载公共属性
            Class<?> refClass = fieldRefAnnotation.value();
            CommonObjectSettingBuilder.load(scope, refClass);
            CommonObjectSetting commonObjectSetting = CommonObjectSettingBuilder.get(scope, refClass.getName());
            if (commonObjectSetting != null) {
                property.setRefCommonKey(commonObjectSetting.getKey());
            }

            return;
        }

        FieldObject fieldObjectAnnotation = field.getAnnotation(FieldObject.class);
        if (fieldObjectAnnotation != null) {
            List<PropertySetting> subProperties = new ArrayList<>();
            buildProperties(scope, subProperties, fieldObjectAnnotation.value(), property.getKey() + ".", parsedClass);
            properties.addAll(subProperties);
        }
    }

    private static void setSelect(PropertySetting property, AnnotatedElement field) {
        FieldSelect fieldSelectAnnotation = field.getAnnotation(FieldSelect.class);
        if (fieldSelectAnnotation == null) {
            return;
        }

        IOptionSetting[] optionConfigs = FieldSelectHelper.getOptions(fieldSelectAnnotation.enumClass());
        if (optionConfigs == null) {
            return;
        }

        List<PropertyOptionSetting> options = new ArrayList<>();
        for (IOptionSetting item : optionConfigs) {
            if (item.isValid()) {
                PropertyOptionSetting option = new PropertyOptionSetting();
                option.setKey(item.getKey());
                option.setName(item.getName());
                option.setDesc(item.getDesc());
                options.add(option);
            }
        }

        property.setOptions(options);
    }

    private static void setEgs(PropertySetting property, AnnotatedElement field) {
        FieldEg[] fieldEgAnnotations = field.getAnnotationsByType(FieldEg.class);
        if (fieldEgAnnotations == null || fieldEgAnnotations.length == 0) {
            return;
        }

        List<PropertyEgSetting> egs = new ArrayList<>();
        for (FieldEg fieldEgAnnotation : fieldEgAnnotations) {
            PropertyEgSetting eg = new PropertyEgSetting();
            eg.setValue(fieldEgAnnotation.eg());
            if (CheckEmptyUtil.isNotEmpty(fieldEgAnnotation.desc())) {
                eg.setDesc(fieldEgAnnotation.desc());
            }
            egs.add(eg);
        }
        property.setEgs(egs);
    }


}
