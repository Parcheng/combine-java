package com.parch.combine.components.data.general.create;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.tools.variable.DataTypeEnum;

public interface DataCreateLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataCreateItem.class)
    @FieldDesc("创建配置项集合")
    @FieldEg(eg = "{\"target\":\"$r.data001.id\",\"type\":\"NUMBER\",\"params\":[\"100000\"]}", desc = "创建一个数字数据为 100000，并将该值赋值到 data001 组件返回结果的 id 字段上")
    @FieldEg(eg = "{\"target\":\"$r.data001.name\",\"type\":\"STRING\",\"params\":[\"zhangsan\"]}", desc = "创建一个字符串数据为 zhangsan，并将该值赋值到 data001 组件返回结果的 name 字段上")
    @FieldEg(eg = "{\"target\":\"$r.data001.deleteFlag\",\"type\":\"BOOLEAN\",\"params\":[\"true\"]}", desc = "创建一个布尔数据为 true，并将该值赋值到 data001 组件返回结果的 deleteFlag 字段上")
    @FieldEg(eg = "{\"target\":\"$r.data001.updateTime\",\"type\":\"DATE\",\"params\":[\"20231023180000\"]}", desc = "创建一个日期数据为 2023 年 10 月 23 日 18 点，并将该值赋值到 data001 组件返回结果的 updateTime 字段上")
    @FieldEg(eg = "{\"target\":\"$r.data001.items\",\"type\":\"LIST\",\"params\":[\"a\",\"a\",\"b\",\"c\"]}", desc = "创建一个集合数据为 [a,a,b,c]，并将该值赋值到 data001 组件返回结果的 items 字段上")
    @FieldEg(eg = "{\"target\":\"$r.data001.settings\",\"type\":\"OBJECT\",\"params\":[\"id:1\",\"type:a\"]}", desc = "创建一个结构对象数据为 {id:1,type:'a'}，并将该值赋值到 data001 组件返回结果的 settings 字段上")
    DataCreateItem[] items();

    interface DataCreateItem {

        @Field(key = "target", name = "创建数据的被赋值变量", type = FieldTypeEnum.EXPRESSION, parseExpression = false)
        String target();

        @Field(key = "type", name = "数据类型", type = FieldTypeEnum.SELECT, isRequired = true)
        @FieldSelect(enumClass = DataTypeEnum.class)
        String type();

        @Field(key = "params", name = "数据值集合", type = FieldTypeEnum.ANY, isRequired = true, isArray = true)
        @FieldDesc("对象类型值必须为“(字段名:字段值)”格式")
        Object[] params();
    }
}
