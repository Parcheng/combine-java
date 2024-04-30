package com.parch.combine.component.data.mapping;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataMappingLogicConfig extends LogicConfig {

    /**
     * 配置项集合
     */
    private List<DataMappingItem> items = new ArrayList<>();

    public List<DataMappingItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            DataMappingItem item = new DataMappingItem();
            item.setNewFieldName(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setSource(ConfigGroupHelper.getConfigByIndex(itemStr,1));
            return item;
        });
    }

    public static class DataMappingItem {
        /**
         * 新字段名
         */
        String newFieldName;

        /**
         * 源数据
         */
        String source;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getNewFieldName() {
            return newFieldName;
        }

        public void setNewFieldName(String newFieldName) {
            this.newFieldName = newFieldName;
        }
    }
}
