package com.parch.combine.core.common.settings.builder;

import com.parch.combine.core.common.settings.config.*;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.annotations.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class PropertySettingBuilder {

    public static List<PropertySetting> build(String scope, Class<?> propertyClass) {
        Set<Class<?>> parsedClass = new HashSet<>();
        List<PropertySetting> properties = new ArrayList<>();
        buildProperties(scope, properties, propertyClass, CheckEmptyUtil.EMPTY, parsedClass);
        return properties;
    }

    private static void buildProperties(String scope, List<PropertySetting> properties, Class<?> propertyClass, String keyPrefix, Set<Class<?>> parsedClass) {
        // 防循环引用
        if (parsedClass.contains(propertyClass)) {
            return;
        }
        parsedClass.add(propertyClass);

        // 解析类
        Class<?> superclass = propertyClass.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            CommonObject componentCommonObject = superclass.getAnnotation(CommonObject.class);
            if (componentCommonObject == null) {
                buildProperties(scope, properties, superclass, keyPrefix, parsedClass);
            } else {
                // 继承公共对象的情况
                PropertySetting commonPropertyRef = new PropertySetting();
                commonPropertyRef.setKey("提示");
                commonPropertyRef.setName("本配置对象支持【" + componentCommonObject.name() + "】的全部配置项");
                commonPropertyRef.setDesc(Collections.singletonList("配置项列表，请参考公共对象【" + componentCommonObject.name() + "】"));
                properties.add(commonPropertyRef);
            }
        }

        // 解析字段
        java.lang.reflect.Field[] fieldArr = propertyClass.getDeclaredFields();
        for (java.lang.reflect.Field field : fieldArr) {
            field.setAccessible(true);
            PropertySetting property = setProperty(properties, field, keyPrefix);
            if (property == null) {
                continue;
            }

            switch (property.getType()) {
                case OBJECT:
                    setObjectProperty(scope, properties, property, field, keyPrefix, parsedClass);
                    break;
                case GROUP:
                    setGroup(property, field);
                    break;
                case SELECT:
                    setSelect(property, field);
                    break;
                default:
                    break;
            }

            setEgs(property, field);
        }
    }

    private static PropertySetting setProperty(List<PropertySetting> properties, java.lang.reflect.Field field, String keyPrefix) {
        Field fieldAnnotation = field.getAnnotation(Field.class);
        if (fieldAnnotation == null || field.getAnnotation(Invalid.class) != null) {
            return null;
        }

        PropertySetting property = new PropertySetting();
        property.setKey(keyPrefix + fieldAnnotation.key());
        property.setName(fieldAnnotation.name());
        property.setType(fieldAnnotation.type());
        property.setIsRequired(fieldAnnotation.isRequired());
        property.setHasExpression(fieldAnnotation.hasExpression());
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

    private static void setObjectProperty(String scope, List<PropertySetting> properties, PropertySetting property, java.lang.reflect.Field field, String keyPrefix, Set<Class<?>> parsedClass) {
        FieldObject fieldObjectAnnotation = field.getAnnotation(FieldObject.class);
        if (fieldObjectAnnotation != null) {
            List<PropertySetting> subProperties = new ArrayList<>();
            buildProperties(scope, subProperties, fieldObjectAnnotation.type(), keyPrefix + property.getKey() + ".", parsedClass);
            properties.addAll(subProperties);
            return;
        }

        FieldRef fieldRefAnnotation = field.getAnnotation(FieldRef.class);
        if (fieldRefAnnotation != null) {
            List<CommonObjectSetting> commonObjectSettings = CommonObjectSettingBuilder.get(scope, fieldRefAnnotation.key());
            if (CheckEmptyUtil.isEmpty(commonObjectSettings)) {
                return;
            }

            if (property.getDesc() == null) {
                property.setDesc(new ArrayList<>());
            }
            StringBuilder refDesc = new StringBuilder("详见：");
            for (CommonObjectSetting componentCommonObjectSetting : commonObjectSettings) {
                refDesc.append("【").append(componentCommonObjectSetting.getName()).append("】");
            }
            property.getDesc().add(refDesc.toString());
        }
    }

    private static void setGroup(PropertySetting property, java.lang.reflect.Field field) {
        FieldGroup[] fieldGroupAnnotations = field.getAnnotationsByType(FieldGroup.class);
        if (fieldGroupAnnotations == null) {
            return;
        }

        List<PropertyGroupSetting> groups = new ArrayList<>();
        for (FieldGroup fieldGroupAnnotation : fieldGroupAnnotations) {
            PropertyGroupSetting group = new PropertyGroupSetting();
            group.setIndex(fieldGroupAnnotation.index());
            group.setName(fieldGroupAnnotation.name());
            group.setType(fieldGroupAnnotation.type());
            group.setIsRequired(fieldGroupAnnotation.isRequired());
            group.setHasExpression(fieldGroupAnnotation.hasExpression());
            groups.add(group);
        }

        FieldGroupSelect[] fieldGroupSelectAnnotations = field.getAnnotationsByType(FieldGroupSelect.class);
        if (fieldGroupSelectAnnotations != null && fieldGroupSelectAnnotations.length > 0) {
            Map<Integer, List<PropertyGroupSetting>> groupMap = groups.stream().collect(Collectors.groupingBy(PropertyGroupSetting::getIndex));
            for (FieldGroupSelect fieldGroupSelectAnnotation : fieldGroupSelectAnnotations) {
                List<PropertyGroupSetting> currGroups = groupMap.get(fieldGroupSelectAnnotation.index());
                if (currGroups == null) {
                    continue;
                }

                for (PropertyGroupSetting currGroup : currGroups) {
                    if (currGroup.getType() != FieldTypeEnum.SELECT) {
                        continue;
                    }

                    IOptionSetting[] optionConfigs = getOptions(fieldGroupSelectAnnotation.enumClass());
                    if (optionConfigs == null || optionConfigs.length == 0) {
                        continue;
                    }

                    List<IOptionSetting> options = new ArrayList<>();
                    List<String> propertyDesc = property.getDesc();
                    propertyDesc.add(currGroup.getName() + " - 可选值：");
                    for (IOptionSetting optionConfig : optionConfigs) {
                        if (!optionConfig.isValid()) {
                            continue;
                        }

                        options.add(optionConfig);

                        propertyDesc.add("【" + optionConfig.getKey() + "】:" + optionConfig.getName() +
                                (CheckEmptyUtil.isEmpty(optionConfig.getDesc()) ? CheckEmptyUtil.EMPTY : ("（" + optionConfig.getDesc() + "）")));
                        if (optionConfig.getDetails() != null) {
                            propertyDesc.addAll(Arrays.asList(optionConfig.getDetails()));
                        }
                    }
                    currGroup.setOptions(options);
                }
            }
        }

        property.setGroup(groups);
    }

    private static void setSelect(PropertySetting property, java.lang.reflect.Field field) {
        FieldSelect fieldSelectAnnotation = field.getAnnotation(FieldSelect.class);
        if (fieldSelectAnnotation == null) {
            return;
        }

        IOptionSetting[] optionConfigs = getOptions(fieldSelectAnnotation.enumClass());
        if (optionConfigs == null) {
            return;
        }

        List<PropertyOptionSetting> options = new ArrayList<>();
        for (IOptionSetting item : optionConfigs) {
            PropertyOptionSetting option = new PropertyOptionSetting();
            option.setKey(item.getKey());
            option.setName(item.getName());
            option.setDesc(item.getDesc());
            options.add(option);
        }

        property.setOptions(options);
    }

    private static void setEgs(PropertySetting property, java.lang.reflect.Field field) {
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

    private static IOptionSetting[] getOptions(Class<?> enumClass) {
        List<PropertyOptionSetting> options = new ArrayList<>();

        try {
            Method method = enumClass.getDeclaredMethod("values");
            method.setAccessible(true);
            return (IOptionSetting[]) method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
