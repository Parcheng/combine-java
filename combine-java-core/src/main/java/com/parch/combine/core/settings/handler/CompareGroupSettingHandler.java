package com.parch.combine.core.settings.handler;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;
import com.parch.combine.core.tools.compare.CompareTypeEnum;

import java.util.Arrays;

/**
 * 比较组设置处理器
 */
public class CompareGroupSettingHandler {

    /**
     * 设置到逻辑配置
     *
     * @param builder 构建对象
     * @param parentKey 属性父KEK
     */
    public static void set(PropertyTypeEnum propertyType, ComponentSettingBuilder builder, String parentKey) {
        String baseKey = CheckEmptyUtil.isEmpty(parentKey) ? "" : (parentKey + ".");

        builder.addProperty(propertyType, baseKey + "relation", FieldTypeEnum.TEXT, "比较类型", false, false, "AND");
        builder.addPropertyDesc(propertyType, baseKey + "relation", "可选值 AND | OR");

        builder.addProperty(propertyType, baseKey + "conditions", FieldTypeEnum.GROUP, "比较条件集合", false, true);
        builder.addPropertyGroup(propertyType, baseKey + "conditions", "比较条件左值", FieldTypeEnum.TEXT,  true, null);
        builder.addPropertyGroup(propertyType, baseKey + "conditions", "比较类型", FieldTypeEnum.SELECT,true, Arrays.asList(CompareTypeEnum.values()));
        builder.addPropertyGroup(propertyType, baseKey + "conditions", "比较条件右值", FieldTypeEnum.TEXT,  false, null);
        builder.addPropertyDesc(propertyType, baseKey + "conditions", "比较条件可选值：");
        for (CompareTypeEnum compareType : CompareTypeEnum.values()) {
            if (compareType.isValid()) {
                builder.addPropertyDesc(propertyType, baseKey + "conditions", compareType.getKey() + ": " + compareType.getName());
            }
        }
    }


    /**
     * 设置到逻辑配置
     *
     * @param builder 构建对象
     * @param parentKey 属性父KEK
     */
    public static void setEg(PropertyTypeEnum propertyType, ComponentSettingBuilder builder, String parentKey) {
        String baseKey = CheckEmptyUtil.isEmpty(parentKey) ? "" : (parentKey + ".");

        builder.addPropertyEg(propertyType, baseKey + "relation", "OR", "所有条件是或的关系，满足任意条件即可");
        builder.addPropertyEg(propertyType, baseKey + "relation", "AND", "所有条件是且的关系，必须满足所有条件才行");
        builder.addPropertyEg(propertyType, baseKey + "conditions", "[\"#{username} NO_NULL\"]", "入参username字段为空，则满足该条件");
    }
}
