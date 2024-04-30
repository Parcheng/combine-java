package com.parch.combine.component.data.create;

import com.parch.combine.core.tools.variable.DataTypeEnum;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataCreateLogicConfig extends LogicConfig {

    /**
     * 配置项集合
     */
    private List<DataCreateItem> items = new ArrayList<>();

    public List<DataCreateItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            DataCreateItem item = new DataCreateItem();
            item.setTarget(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setType(DataTypeEnum.get(ConfigGroupHelper.getConfigByIndex(itemStr,1)));
            item.setParams(Arrays.asList(ConfigGroupHelper.getConfigsByIndex(itemStr, 2, itemStr.length -1)));
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
