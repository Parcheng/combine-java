package com.parch.combine.component.data.format;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataFormatLogicConfig extends LogicConfig {

    /**
     * 是否覆盖原值
     */
    private Boolean replace;

    /**
     * 配置项集合
     */
    private List<DataFormatItem> items = new ArrayList<>();

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
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            DataFormatItem item = new DataFormatItem();
            item.setFieldName(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setFunction(DataFormatFunctionEnum.get(ConfigGroupHelper.getConfigByIndex(itemStr,1)));
            item.setParams(Arrays.asList(ConfigGroupHelper.getConfigsByIndex(itemStr,2)));
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
