package com.parch.combine.components.data.general.edit;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.ConfigGroupTool;
import com.parch.combine.core.component.tools.variable.DataTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface DataEditLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataEditItem.class)
    @FieldDesc("编辑配置项集合")
    @FieldEg(eg = "{\"target\":\"id\",\"type\":\"SET\",\"dataType\":\"NUMBER\",\"params\":[\"101\"]}", desc = "修改参数中 id 字段的值为 101")
    @FieldEg(eg = "{\"target\":\"name\",\"type\":\"SET\",\"dataType\":\"STRING\",\"params\":[\"TEST\"]}", desc = "修改参数中 name 字段的值为 TEST")
    @FieldEg(eg = "{\"target\":\"time\",\"type\":\"SET\",\"dataType\":\"DATE\",\"params\":[\"20231101180000\"]}", desc = "修改参数中 time 字段的值为 20231101180000（时间毫秒值）")
    @FieldEg(eg = "{\"target\":\"list1\",\"type\":\"ADD\",\"dataType\":\"NUMBER\",\"params\":[\"80\",\"90\",\"100\",\"#{$v.addData1}\"]}", desc = "为参数中 list1 集合添加多个数字 80、90、100及流程中变量 addData1 字段的值")
    @FieldEg(eg = "{\"target\":\"list1\",\"type\":\"SET\",\"dataType\":\"NUMBER\",\"params\":[\"(1:20)\",\"(2:#{$v.val1})\"]}", desc = "为参数中 list1 集合索引为 1 的值设置为 20, 索引为 2 的值设置流程中变量 val1 字段的值")
    @FieldEg(eg = "{\"target\":\"list1\",\"type\":\"REMOVE_INDEX\",\"params\":[\"4\"]}", desc = "删除参数中 list1 集合索引为 4 的值（集合后面的数据会前移）")
    @FieldEg(eg = "{\"target\":\"list1\",\"type\":\"REMOVE\",\"params\":[\"40\"]}", desc = "删除参数中 list1 集合中值 40 的项（集合后面的数据会前移）")
    @FieldEg(eg = "{\"target\":\"list1\",\"type\":\"REMOVE_ALL\",\"params\":[\"60\",\"#{$v.val1}\"]}", desc = "删除参数中 list1 集合中值 60 的项, 及值等于流程中变量 val1 字段的项")
    @FieldEg(eg = "{\"target\":\"data\",\"type\":\"PUT\",\"params\":[\"(NUMBER:status:1)\",\"#{$v.addData}\"]}", desc = "为参数中 data 结构对象添加一个字段名为 status, 值为 1 的数字, 并将流程中变量的 addData 结构对象的所有字段添加进去")
    @FieldEg(eg = "{\"target\":\"data\",\"type\":\"REMOVE\",\"params\":[\"code\"]}", desc = "删除参数中 data 结构对象 code 字段")
    @FieldEg(eg = "{\"target\":\"data\",\"type\":\"REMOVE_INDEX\",\"params\":[\"code2\"]}", desc = "删除参数中 data 结构对象 code2 字段")
    @FieldEg(eg = "{\"target\":\"data\",\"type\":\"REMOVE_ALL\",\"params\":[\"flag\",\"#{$v.list}\",\"#{$v.data}\"]}", desc = "删除参数中 data 结构对象 flag 字段，及流程中变量的 list 集合对应的所有字段，及流程中变量的 data 结构对象中的所有字段")
    DataEditItem[] items();

    interface DataEditItem {

        @Field(key = "source", name = "要编辑的数据源", type = FieldTypeEnum.EXPRESSION, parseExpression = false, isRequired = true)
        String source();

        @Field(key = "type", name = "数据编辑方式", type = FieldTypeEnum.SELECT, isRequired = true)
        @FieldSelect(enumClass = DataEditTypeEnum.class)
        String type();

        @Field(key = "dataType", name = "数据类型", type = FieldTypeEnum.SELECT)
        @FieldSelect(enumClass = DataTypeEnum.class)
        String dataType();

        @Field(key = "params", name = "参数", type = FieldTypeEnum.ANY, isRequired = true, isArray = true)
        Object[] params();
    }
}
