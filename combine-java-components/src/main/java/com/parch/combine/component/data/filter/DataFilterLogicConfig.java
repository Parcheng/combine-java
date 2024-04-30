package com.parch.combine.component.data.filter;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataFilterLogicConfig extends LogicConfig {

    /**
     * 结果ID
     */
    private String resultId;

    /**
     * 配置项集合
     */
    private List<DataFilterItem> items = new ArrayList<>();

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public List<DataFilterItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            DataFilterItem item = new DataFilterItem();
            item.setFieldName(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setRule(ConfigGroupHelper.getConfigByIndex(itemStr,1));
            item.setRuleParams(Arrays.asList(ConfigGroupHelper.getConfigsByIndex(itemStr,2)));
            return item;
        });
    }

    /**
     * 过滤配置项
     */
    public static class DataFilterItem {

        /**
         * 字段名
         */
        String fieldName;

        /**
         * 规则
         */
        String rule;

        /**
         * 规则参数
         */
        List<String> ruleParams;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public List<String> getRuleParams() {
            return ruleParams;
        }

        public void setRuleParams(List<String> ruleParams) {
            this.ruleParams = ruleParams;
        }
    }
}
