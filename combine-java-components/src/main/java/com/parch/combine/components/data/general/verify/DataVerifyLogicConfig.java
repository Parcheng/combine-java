package com.parch.combine.components.data.general.verify;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataVerifyLogicConfig extends LogicConfig {

    @Field(key = "mode", name = "验证模式", type = FieldTypeEnum.SELECT, defaultValue = "false")
    @FieldSelect(enumClass = VerifyModeEnum.class)
    private String mode;

    @Field(key = "defaultMsg", name = "默认错误提示信息", type = FieldTypeEnum.TEXT)
    @FieldDesc("会拼接在 items 中配置错误提示信息之前")
    private String defaultMsg;

    @Field(key = "items", name = "格式化配置集合，", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    @FieldObject(type = DataVerifyItem.class)
    private List<DataVerifyItem> items = new ArrayList<>();

    public static class DataVerifyItem extends CompareGroupConfig {

        @Field(key = "msg", name = "错误提示信息，", type = FieldTypeEnum.TEXT, hasExpression = true, isRequired = true)
        @FieldObject(type = DataVerifyItem.class)
        @FieldEg(eg = "名称不正确", desc = "条件成立时，返回“名称不正确”错误信息")
        @FieldEg(eg = "#{$r.data001.error}", desc = "条件成立时，返回 data001 组件返回结果的 error 字段的值")
        String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(getMode())) {
            setMode(VerifyModeEnum.FIRST.name());
        }
    }

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
}
