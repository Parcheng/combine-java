package com.parch.combine.components.data.general.format;

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
public class DataFormatLogicConfig extends LogicConfig {

    @Field(key = "replace", name = "是否替换源数据", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean replace;

    @Field(key = "items", name = "过滤配置集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldDesc("创建配置项集合")
    @FieldGroup(index = 0, name = "要格式化的字段", type = FieldTypeEnum.EXPRESSION)
    @FieldGroup(index = 1, name = "函数", type = FieldTypeEnum.SELECT)
    @FieldGroupSelect(index = 1, enumClass = DataFormatFunctionEnum.class)
    @FieldGroup(index = 2, name = "函数的参数", type = FieldTypeEnum.TEXT, isRequired = false)
    @FieldEg(eg = "$r.data001.extInfo JSON TO_JSON", desc = "将组件 data001 的 extInfo 字段，转换成 JSON 字符串")
    private List<DataFormatItem> items = new ArrayList<>();

    @Override
    public void init() {
        if (getReplace() == null) {
            setReplace(false);
        }
    }

    public Boolean getReplace() {
        return replace;
    }

    public void setReplace(Boolean replace) {
        this.replace = replace;
    }

    public List<DataFormatItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            DataFormatItem item = new DataFormatItem();
            item.setFieldName(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setFunction(DataFormatFunctionEnum.get(ConfigGroupTool.getConfigByIndex(itemStr,1)));
            item.setParams(Arrays.asList(ConfigGroupTool.getConfigsByIndex(itemStr,2)));
            return item;
        });
    }

    public static class DataFormatItem {
        /**
         * 新字段名
         */
        private String fieldName;

        /**
         * 函数
         */
        private DataFormatFunctionEnum function;

        /**
         * 参数
         */
        private List<String> params;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public DataFormatFunctionEnum getFunction() {
            return function;
        }

        public void setFunction(DataFormatFunctionEnum function) {
            this.function = function;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }
    }
}
