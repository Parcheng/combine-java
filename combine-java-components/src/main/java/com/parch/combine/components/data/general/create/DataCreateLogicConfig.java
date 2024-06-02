package com.parch.combine.components.data.general.create;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.variable.DataTypeEnum;
import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.component.tools.ConfigGroupTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataCreateLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldDesc("创建配置项集合")
    @FieldGroup(index = 0, name = "创建数据的被赋值变量", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 1, name = "数据类型", type = FieldTypeEnum.SELECT)
    @FieldGroupSelect(index = 1, enumClass = DataTypeEnum.class)
    @FieldGroup(index = 2, name = "数据值。如果是集合或对象类型值可以有多个（空格分隔），对象类型值必须为“字段名:字段值”格式", type = FieldTypeEnum.TEXT)
    @FieldEg(eg = "$r.data001.id NUMBER 100000", desc = "创建一个数字数据为 100000，并将该值赋值到 data001 组件返回结果的 id 字段上")
    @FieldEg(eg = "$r.data001.name STRING zhangsan", desc = "创建一个字符串数据为 zhangsan，并将该值赋值到 data001 组件返回结果的 name 字段上")
    @FieldEg(eg = "$r.data001.deleteFlag BOOLEAN true", desc = "创建一个布尔数据为 true，并将该值赋值到 data001 组件返回结果的 deleteFlag 字段上")
    @FieldEg(eg = "$r.data001.updateTime DATE 20231023180000", desc = "创建一个日期数据为 2023 年 10 月 23 日 18 点，并将该值赋值到 data001 组件返回结果的 updateTime 字段上")
    @FieldEg(eg = "$r.data001.items LIST a a b c", desc = "创建一个集合数据为 [a,a,b,c]，并将该值赋值到 data001 组件返回结果的 items 字段上")
    @FieldEg(eg = "$r.data001.settings OBJECT id:1 type:a", desc = "创建一个结构对象数据为 {id:1,type:'a'}，并将该值赋值到 data001 组件返回结果的 settings 字段上")
    private List<DataCreateItem> items = new ArrayList<>();

    @Override
    public void init() {}

    public List<DataCreateItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            DataCreateItem item = new DataCreateItem();
            item.setTarget(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setType(DataTypeEnum.get(ConfigGroupTool.getConfigByIndex(itemStr,1)));
            item.setParams(Arrays.asList(ConfigGroupTool.getConfigsByIndex(itemStr, 2, itemStr.length -1)));
            return item;
        });
    }

    public static class DataCreateItem {
        /**
         * 运算结果的存储字段
         */
        String target;

        /**
         * 运算方式
         */
        DataTypeEnum type;

        /**
         * 参数
         */
        List<String> params;

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public DataTypeEnum getType() {
            return type;
        }

        public void setType(DataTypeEnum type) {
            this.type = type;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }
    }
}
