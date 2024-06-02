package com.parch.combine.components.logic.error;

import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicErrorLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.ANY, isArray = true, isRequired = true)
    @FieldObject(type = LogicErrorItem.class)
    private List<LogicErrorItem> items;

    @Override
    public void init() {}

    /**
     * 逻辑判断配置项
     */
    public static class LogicErrorItem extends CompareGroupConfig {

        @Field(key = "errorMsg", name = "错误信息", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        @FieldEg(eg = "空指针异常", desc = "条件满足后执行会报错，返回的 errorMsg 为该配置项的信息")
        private String errorMsg;

        @Field(key = "showMsg", name = "显示的错误信息", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        @FieldEg(eg = "系统内部错误", desc = "条件满足后执行会报错，返回的 showMsg 为该配置项的信息")
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
