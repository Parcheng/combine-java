package com.parch.combine.component.data.verify;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataVerifyLogicConfig extends LogicConfig {

    /**
     * 模式
     */
    private String mode;

    /**
     * 默认错误信息
     */
    private String defaultMsg;

    /**
     * 配置项集合
     */
    private List<DataVerifyItem> items = new ArrayList<>();

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    public void setDefaultMsg(String defaultMsg) {
        this.defaultMsg = defaultMsg;
    }

    public List<DataVerifyItem> getItems() {
        return items;
    }

    public void setItems(List<DataVerifyItem> items) {
        this.items = items;
    }

    public static class DataVerifyItem extends CompareGroupConfig {

        /**
         * 异常信息
         */
        String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
