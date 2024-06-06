package com.parch.combine.components.data.general.verify;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

public interface DataVerifyLogicConfig extends ILogicConfig {

    @Field(key = "mode", name = "验证模式", type = FieldTypeEnum.SELECT, defaultValue = "FIRST")
    @FieldSelect(enumClass = VerifyModeEnum.class)
    String mode();

    @Field(key = "defaultMsg", name = "默认错误提示信息", type = FieldTypeEnum.TEXT)
    @FieldDesc("会拼接在 items 中配置错误提示信息之前")
    String defaultMsg();

    @Field(key = "items", name = "格式化配置集合，", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataVerifyItem.class)
    DataVerifyItem[] items();

    interface DataVerifyItem {

        @Field(key = "compare", name = "比较条件配置，", type = FieldTypeEnum.OBJECT)
        @FieldObject(CompareGroupConfig.class)
        CompareGroupConfig compare();

        @Field(key = "msg", name = "错误提示信息，", type = FieldTypeEnum.TEXT, isRequired = true)
        @FieldObject(DataVerifyItem.class)
        @FieldEg(eg = "名称不正确", desc = "条件成立时，返回“名称不正确”错误信息")
        @FieldEg(eg = "#{$r.data001.error}", desc = "条件成立时，返回 data001 组件返回结果的 error 字段的值")
        String msg();
    }
}
