package com.parch.combine.component.data.filter;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class DataFilterSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("filter", "数据过滤组件", false, DataFilterComponent.class);
        // builder.addDesc("数据过滤");

        builder.addProperty(PropertyTypeEnum.LOGIC, "resultId", FieldTypeEnum.GROUP, "其他组件ID",  false, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "resultId", "指定了该参数，则会将 resultId 对应组件的执行结果作为该组件的执行结果返回，如果未指定则默认使用上一步组件的结果");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "过滤配置集合",  false, true);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "要过滤的字段的路径", FieldTypeEnum.TEXT,  true, null);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "过滤规则，默认为 CLEAR 规则", FieldTypeEnum.SELECT,true, Arrays.asList(DataFilterRuleEnum.values()));
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "过滤规则的参数", FieldTypeEnum.TEXT,  false, null);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.id", "将 data001 组件返回结果的 id 字段清除掉");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.name REPLACE zhangsan", "将 data001 组件返回结果的 name 字段的值替换为 zhangsan");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.type REPLACE #{$c.type}", "表示将 data001 组件返回结果的 name 字段的值替换为全局变量的 type 字段值");


        builder.setResult("参数 resultId 指定组件的结果，或上一步组件的结果", false);

        return builder.get();
    }
}
