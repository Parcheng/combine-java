package com.parch.combine.core.settings.builder;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.settings.config.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ComponentPropertySettingBuilder {

    public static List<ComponentPropertySetting> build(String scope, Class<?> propertyClass) {
        Set<Class<?>> parsedClass = new HashSet<>();
        List<ComponentPropertySetting> properties = new ArrayList<>();
        buildProperties(scope, properties, propertyClass, CheckEmptyUtil.EMPTY, parsedClass);
        return properties;
    }

    private static void buildProperties(String scope, List<ComponentPropertySetting> properties, Class<?> propertyClass, String keyPrefix, Set<Class<?>> parsedClass) {
        // 防循环引用
        if (parsedClass.contains(propertyClass)) {
            return;
        }
        parsedClass.add(propertyClass);

        // 解析类
        Class<?> superclass = propertyClass.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            ComponentCommonObject componentCommonObject = superclass.getAnnotation(ComponentCommonObject.class);
            if (componentCommonObject == null) {
                buildProperties(scope, properties, superclass, keyPrefix, parsedClass);
            } else {
                // 继承公共对象的情况
                ComponentPropertySetting commonPropertyRef = new ComponentPropertySetting();
                commonPropertyRef.setKey("提示");
                commonPropertyRef.setName("本配置对象支持【" + componentCommonObject.name() + "】的全部配置项");
                commonPropertyRef.setDesc(Collections.singletonList("配置项列表，请参考公共对象【" + componentCommonObject.name() + "】"));
                properties.add(commonPropertyRef);
            }
        }

        // 解析字段
        Field[] fieldArr = propertyClass.getDeclaredFields();
        for (Field field : fieldArr) {
            field.setAccessible(true);
            ComponentPropertySetting property = setProperty(properties, field, keyPrefix);
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

    private static ComponentPropertySetting setProperty(List<ComponentPropertySetting> properties, Field field, String keyPrefix) {
        ComponentField fieldAnnotation = field.getAnnotation(ComponentField.class);
        if (fieldAnnotation == null || field.getAnnotation(Invalid.class) != null) {
            return null;
        }

        ComponentPropertySetting property = new ComponentPropertySetting();
        property.setKey(keyPrefix + fieldAnnotation.key());
        property.setName(fieldAnnotation.name());
        property.setType(fieldAnnotation.type());
        property.setIsRequired(fieldAnnotation.isRequired());
        property.setIsArray(fieldAnnotation.isArray());
        property.setDesc(new ArrayList<>());
        property.setDefaultValue(fieldAnnotation.defaultValue());

        ComponentFieldDesc fieldDescAnnotation = field.getAnnotation(ComponentFieldDesc.class);
        if (fieldDescAnnotation != null) {
            property.getDesc().addAll(Arrays.asList(fieldDescAnnotation.value()));
        }

        properties.add(property);
        return property;
    }

    private static void setObjectProperty(String scope, List<ComponentPropertySetting> properties, ComponentPropertySetting property, Field field, String keyPrefix, Set<Class<?>> parsedClass) {
        ComponentFieldObject fieldObjectAnnotation = field.getAnnotation(ComponentFieldObject.class);
        if (fieldObjectAnnotation != null) {
            List<ComponentPropertySetting> subProperties = new ArrayList<>();
            buildProperties(scope, subProperties, fieldObjectAnnotation.type(), keyPrefix + property.getKey() + ".", parsedClass);
            properties.addAll(subProperties);
            return;
        }

        ComponentFieldRef fieldRefAnnotation = field.getAnnotation(ComponentFieldRef.class);
        if (fieldRefAnnotation != null) {
            List<ComponentCommonObjectSetting> commonObjectSettings = ComponentCommonObjectSettingBuilder.get(scope, fieldRefAnnotation.key());
            if (CheckEmptyUtil.isEmpty(commonObjectSettings)) {
                return;
            }

            if (property.getDesc() == null) {
                property.setDesc(new ArrayList<>());
            }
            StringBuilder refDesc = new StringBuilder("详见：");
            for (ComponentCommonObjectSetting componentCommonObjectSetting : commonObjectSettings) {
                refDesc.append("【").append(componentCommonObjectSetting.getName()).append("】");
            }
            property.getDesc().add(refDesc.toString());
        }
    }

    private static void setGroup(ComponentPropertySetting property, Field field) {
        ComponentFieldGroup[] fieldGroupAnnotations = field.getAnnotationsByType(ComponentFieldGroup.class);
        if (fieldGroupAnnotations == null) {
            return;
        }

        List<ComponentPropertyGroupSetting> groups = new ArrayList<>();
        for (ComponentFieldGroup fieldGroupAnnotation : fieldGroupAnnotations) {
            ComponentPropertyGroupSetting group = new ComponentPropertyGroupSetting();
            group.setIndex(fieldGroupAnnotation.index());
            group.setName(fieldGroupAnnotation.name());
            group.setType(fieldGroupAnnotation.type());
            group.setIsRequired(fieldGroupAnnotation.isRequired());
            groups.add(group);
        }

        ComponentFieldGroupSelect[] fieldGroupSelectAnnotations = field.getAnnotationsByType(ComponentFieldGroupSelect.class);
        if (fieldGroupSelectAnnotations != null && fieldGroupSelectAnnotations.length > 0) {
            Map<Integer, List<ComponentPropertyGroupSetting>> groupMap = groups.stream().collect(Collectors.groupingBy(ComponentPropertyGroupSetting::getIndex));
            for (ComponentFieldGroupSelect fieldGroupSelectAnnotation : fieldGroupSelectAnnotations) {
                List<ComponentPropertyGroupSetting> currGroups = groupMap.get(fieldGroupSelectAnnotation.index());
                if (currGroups == null) {
                    continue;
                }

                for (ComponentPropertyGroupSetting currGroup : currGroups) {
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

    private static void setSelect(ComponentPropertySetting property, Field field) {
        ComponentFieldSelect fieldSelectAnnotation = field.getAnnotation(ComponentFieldSelect.class);
        if (fieldSelectAnnotation == null) {
            return;
        }

        IOptionSetting[] optionConfigs = getOptions(fieldSelectAnnotation.enumClass());
        if (optionConfigs == null) {
            return;
        }

        List<ComponentPropertyOptionSetting> options = new ArrayList<>();
        for (IOptionSetting item : optionConfigs) {
            ComponentPropertyOptionSetting option = new ComponentPropertyOptionSetting();
            option.setKey(item.getKey());
            option.setName(item.getName());
            option.setDesc(item.getDesc());
            options.add(option);
        }

        property.setOptions(options);
    }

    private static void setEgs(ComponentPropertySetting property, Field field) {
        ComponentFieldEg[] fieldEgAnnotations = field.getAnnotationsByType(ComponentFieldEg.class);
        if (fieldEgAnnotations == null || fieldEgAnnotations.length == 0) {
            return;
        }

        List<ComponentPropertyEgSetting> egs = new ArrayList<>();
        for (ComponentFieldEg fieldEgAnnotation : fieldEgAnnotations) {
            ComponentPropertyEgSetting eg = new ComponentPropertyEgSetting();
            eg.setValue(fieldEgAnnotation.eg());
            if (CheckEmptyUtil.isNotEmpty(fieldEgAnnotation.desc())) {
                eg.setDesc(fieldEgAnnotation.desc());
            }
            egs.add(eg);
        }
        property.setEgs(egs);
    }

    private static IOptionSetting[] getOptions(Class<?> enumClass) {
        List<ComponentPropertyOptionSetting> options = new ArrayList<>();

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
