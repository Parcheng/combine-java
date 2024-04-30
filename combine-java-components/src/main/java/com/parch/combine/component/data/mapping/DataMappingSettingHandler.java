package com.parch.combine.component.data.mapping;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class DataMappingSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("mapping", "数据映射组件", false, DataMappingComponent.class);
        // builder.addDesc("数据映射");

        builder.addProperty(PropertyTypeEnum.LOGIC, "replace", FieldTypeEnum.BOOLEAN, "是否替换源数据（默认不替换）",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "映射配置项集合",  true, true);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "字段名", FieldTypeEnum.TEXT,  true, null);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "取值目标的字段或常量值", FieldTypeEnum.TEXT,  true, null);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items","id #{id}", "将入参的 id 参数的值，赋值给执行结果的 id 字段");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items","name #{$r.data001.name}", "将 data001 组件返回结果的 name 字段的值，赋值给执行结果的 name 字段");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items","age 18", "将 18 赋值给执行结果的 age 字段");


        builder.setResult("由 items 中配置的所有 “字段名” 组成的对象", false);
        return builder.get();
    }
}
