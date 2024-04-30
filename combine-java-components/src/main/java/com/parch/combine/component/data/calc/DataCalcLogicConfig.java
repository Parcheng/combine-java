package com.parch.combine.component.data.calc;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataCalcLogicConfig extends LogicConfig {

    /**
     * 配置项集合
     */
    private List<DataCalcItem> items = new ArrayList<>();

    public List<DataCalcItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            DataCalcItem item = new DataCalcItem();
            item.setTarget(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setMode(ConfigGroupHelper.getConfigByIndex(itemStr,1));
            item.setParams(Arrays.asList(ConfigGroupHelper.getConfigsByIndex(itemStr, 2, itemStr.length -1)));
            return item;
        });
    }

    public static class DataCalcItem {
        /**
         * 运算结果的存储字段
         */
        String target;

        /**
         * 运算方式
         */
        String mode;

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

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }
    }
}
