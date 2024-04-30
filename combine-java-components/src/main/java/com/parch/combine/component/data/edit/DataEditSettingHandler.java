package com.parch.combine.component.data.edit;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;
import com.parch.combine.core.tools.variable.DataTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class DataEditSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("edit", "数据修改组件", false, DataEditComponent.class);
        // builder.addDesc("数据修改");

        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "配置项集合",  true, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "创建配置项集合");
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "创建数据的被赋值变量", FieldTypeEnum.TEXT,  true, null);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "数据类型", FieldTypeEnum.SELECT,true, Arrays.asList(DataTypeEnum.values()));
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "数据值。如果是集合或对象类型值可以有多个（空格分隔），对象类型值必须为“字段名:字段值”格式", FieldTypeEnum.TEXT,  false, null);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.id NUMBER 100000", "创建一个数字数据为 100000，并将该值赋值到 data001 组件返回结果的 id 字段上");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.name STRING zhangsan", "创建一个字符串数据为 zhangsan，并将该值赋值到 data001 组件返回结果的 name 字段上");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.deleteFlag BOOLEAN true", "创建一个布尔数据为 true，并将该值赋值到 data001 组件返回结果的 deleteFlag 字段上");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.updateTime DATE 20231023180000", "创建一个日期数据为 2023 年 10 月 23 日 18 点，并将该值赋值到 data001 组件返回结果的 updateTime 字段上");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.items LIST a a b c", "创建一个集合数据为 [a,a,b,c]，并将该值赋值到 data001 组件返回结果的 items 字段上");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.settings OBJECT id:1 type:a", "创建一个结构对象数据为 {id:1,type:'a'}，并将该值赋值到 data001 组件返回结果的 settings 字段上");


        builder.setResult("所有被创建的数据集合", false);

        return builder.get();
    }
}
