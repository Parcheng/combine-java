package com.parch.combine.components.logic.error;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicErrorLogicConfig extends LogicConfig {

    @ComponentField(key = "items", name = "配置项集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @ComponentFieldObject(type = LogicErrorItem.class)
    private List<LogicErrorItem> items;

    /**
     * 逻辑判断配置项
     */
    public static class LogicErrorItem extends CompareGroupConfig {

        @ComponentField(key = "errorMsg", name = "错误信息", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        @ComponentFieldEg(eg = "空指针异常", desc = "条件满足后执行会报错，返回的 errorMsg 为该配置项的信息")
        private String errorMsg;

        @ComponentField(key = "showMsg", name = "显示的错误信息", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        @ComponentFieldEg(eg = "系统内部错误", desc = "条件满足后执行会报错，返回的 showMsg 为该配置项的信息")
        private String showMsg;

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

    public List<LogicErrorItem> getItems() {
        return items;
    }

    public void setItems(List<LogicErrorItem> items) {
        this.items = items;
    }
}
