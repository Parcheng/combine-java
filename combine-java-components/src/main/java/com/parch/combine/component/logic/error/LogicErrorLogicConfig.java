package com.parch.combine.component.logic.error;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicErrorLogicConfig extends LogicConfig {

    /**
     * 逻辑判断集合
     */
    private List<LogicErrorItem> items;

    public List<LogicErrorItem> getItems() {
        return items;
    }

    public void setItems(List<LogicErrorItem> items) {
        this.items = items;
    }

    /**
     * 逻辑判断配置项
     */
    public static class LogicErrorItem extends CompareGroupConfig {
        /**
         * 错误信息
         */
        String errorMsg;

        /**
         * 显示错误信息
         */
        String showMsg;

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getShowMsg() {
            return showMsg;
        }

        public void setShowMsg(String showMsg) {
            this.showMsg = showMsg;
        }
    }
}
