package com.parch.combine.components.data.general.filter;

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
public class DataFilterLogicConfig extends LogicConfig {

    @Field(key = "resultId", name = "其他组件ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("如果指定了该参数，则会将 resultId 对应组件的执行结果作为该组件的执行结果返回，如果未指定则默认使用上一步组件的结果")
    private String resultId;

    @Field(key = "items", name = "过滤配置集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldDesc("创建配置项集合")
    @FieldGroup(index = 0, name = "要过滤的字段的路径", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 1, name = "过滤规则，默认为 CLEAR 规则", type = FieldTypeEnum.SELECT, isRequired = false)
    @FieldGroupSelect(index = 1, enumClass = DataFilterRuleEnum.class)
    @FieldGroup(index = 2, name = "过滤规则的参数", type = FieldTypeEnum.TEXT, isRequired = false)
    @FieldEg(eg = "$r.data001.id", desc = "将 data001 组件返回结果的 id 字段清除掉")
    @FieldEg(eg = "$r.data001.name REPLACE zhangsan", desc = "将 data001 组件返回结果的 name 字段的值替换为 zhangsan")
    @FieldEg(eg = "$r.data001.type REPLACE #{$c.type}", desc = "表示将 data001 组件返回结果的 name 字段的值替换为全局变量的 type 字段值")
    private List<DataFilterItem> items = new ArrayList<>();

    @Override
    public void init() {}

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
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            DataFilterItem item = new DataFilterItem();
            item.setFieldName(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setRule(ConfigGroupTool.getConfigByIndex(itemStr,1));
            item.setRuleParams(Arrays.asList(ConfigGroupTool.getConfigsByIndex(itemStr,2)));
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
