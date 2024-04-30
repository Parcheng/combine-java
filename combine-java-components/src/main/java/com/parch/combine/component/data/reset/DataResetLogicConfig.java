package com.parch.combine.component.data.reset;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataResetLogicConfig extends LogicConfig {

    /**
     * 配置项集合
     */
    private List<DataResetItem> items = new ArrayList<>();

    public List<DataResetItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            DataResetItem item = new DataResetItem();
            item.setFieldName(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setType(ConfigGroupHelper.getConfigByIndex(itemStr,1));
            item.setValue(ConfigGroupHelper.getConfigByIndex(itemStr,2));
            return item;
        });
    }

    public static class DataResetItem {
        /**
         * 字段名
         */
        String fieldName;

        /**
         * 字段类型
         */
        String type;

        /**
         * 值
         */
        String value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
