package com.parch.combine.component.logic.judgment;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicJudgmentLogicConfig extends LogicConfig {

    /**
     * 逻辑判断集合
     */
    private List<LogicJudgmentItem> items;

    public List<LogicJudgmentItem> getItems() {
        return items;
    }

    public void setItems(List<LogicJudgmentItem> items) {
        this.items = items;
    }

    /**
     * 逻辑判断配置项
     */
    public static class LogicJudgmentItem extends CompareGroupConfig {
        /**
         * 规则参数
         */
        List<Object> components;

        public List<Object> getComponents() {
            return components;
        }

        public void setComponents(List<Object> components) {
            this.components = components;
        }
    }
}
