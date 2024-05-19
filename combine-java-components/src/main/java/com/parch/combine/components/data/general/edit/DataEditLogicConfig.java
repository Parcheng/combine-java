package com.parch.combine.components.data.general.edit;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.ConfigGroupTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataEditLogicConfig extends LogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldDesc("编辑配置项集合")
    @FieldGroup(index = 0, name = "要编辑的数据源", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 1, name = "数据编辑方式", type = FieldTypeEnum.SELECT)
    @FieldGroupSelect(index = 1, enumClass = DataEditTypeEnum.class)
    @FieldGroup(index = 2, name = "参数，多个使用空格分隔", type = FieldTypeEnum.TEXT)
    @FieldEg(eg = "id SET NUMBER :101", desc = "修改参数中 id 字段的值为 101")
    @FieldEg(eg = "name SET STRING :TEST", desc = "修改参数中 name 字段的值为 TEST")
    @FieldEg(eg = "time SET DATE :20231101180000", desc = "修改参数中 time 字段的值为 20231101180000（时间毫秒值）")
    @FieldEg(eg = "list1 ADD NUMBER 70", desc = "为参数中 list1 集合添加一个数字 70")
    @FieldEg(eg = "list1 ADD_ALL NUMBER 80 90 100 #{$v.addData1}", desc = "为参数中 list1 集合添加多个数字 80、90、100及流程中变量 addData1 字段的值")
    @FieldEg(eg = "list1 SET NUMBER 0:10", desc = "为参数中 list1 集合索引为 0 的值设置为 10")
    @FieldEg(eg = "list1 SET_ALL NUMBER 1:20 2:#{$v.val1}", desc = "为参数中 list1 集合索引为 1 的值设置为 20, 索引为 2 的值设置流程中变量 val1 字段的值")
    @FieldEg(eg = "list1 REMOVE_INDEX 4", desc = "删除参数中 list1 集合索引为 4 的值（集合后面的数据会前移）")
    @FieldEg(eg = "list1 REMOVE 40", desc = "删除参数中 list1 集合中值 40 的项（集合后面的数据会前移）")
    @FieldEg(eg = "list1 REMOVE_ALL 60 #{$v.val1}", desc = "删除参数中 list1 集合中值 60 的项, 及值等于流程中变量 val1 字段的项")
    @FieldEg(eg = "data PUT STRING:dataType:txt", desc = "为参数中 data 结构对象添加一个字段名为 dataType, 值为 txt 的字符串")
    @FieldEg(eg = "data PUT_ALL NUMBER:status:1 #{$v.addData}", desc = "为参数中 data 结构对象添加一个字段名为 status, 值为 1 的数字, 并将流程中变量的 addData 结构对象的所有字段添加进去")
    @FieldEg(eg = "data REMOVE code", desc = "删除参数中 data 结构对象 code 字段")
    @FieldEg(eg = "data REMOVE_INDEX code2", desc = "删除参数中 data 结构对象 code2 字段")
    @FieldEg(eg = "data REMOVE_ALL flag #{$v.list} #{$v.data}", desc = "删除参数中 data 结构对象 flag 字段，及流程中变量的 list 集合对应的所有字段，及流程中变量的 data 结构对象中的所有字段")
    private List<DataEditItem> items = new ArrayList<>();

    @Override
    public void init() {}

    public List<DataEditItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            DataEditItem item = new DataEditItem();
            item.setSource(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setType(DataEditTypeEnum.get(ConfigGroupTool.getConfigByIndex(itemStr,1)));
            item.setParams(Arrays.asList(ConfigGroupTool.getConfigsByIndex(itemStr, 2, itemStr.length -1)));
            return item;
        });
    }

    public static class DataEditItem {
        /**
         * 数据来源
         */
        String source;

        /**
         * 编辑类型
         */
        DataEditTypeEnum type;

        /**
         * 参数
         */
        List<String> params;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public DataEditTypeEnum getType() {
            return type;
        }

        public void setType(DataEditTypeEnum type) {
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
