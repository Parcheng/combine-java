package com.parch.combine.component.data.edit;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataEditLogicConfig extends LogicConfig {

    /**
     * 配置项集合
     */
    private List<DataEditItem> items = new ArrayList<>();

    public List<DataEditItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            DataEditItem item = new DataEditItem();
            item.setSource(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setType(DataEditTypeEnum.get(ConfigGroupHelper.getConfigByIndex(itemStr,1)));
            item.setParams(Arrays.asList(ConfigGroupHelper.getConfigsByIndex(itemStr, 2, itemStr.length -1)));
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
