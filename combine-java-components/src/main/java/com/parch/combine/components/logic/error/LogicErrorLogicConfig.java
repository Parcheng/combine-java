package com.parch.combine.components.logic.error;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

public interface LogicErrorLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(LogicErrorItem.class)
    LogicErrorItem[] items();

    interface LogicErrorItem {

        @Field(key = "compare", name = "比较条件配置", type = FieldTypeEnum.OBJECT)
        @FieldObject(CompareGroupConfig.class)
        CompareGroupConfig compare();

        @Field(key = "errorMsg", name = "错误信息", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        @FieldEg(eg = "空指针异常", desc = "条件满足后执行会报错，返回的 errorMsg 为该配置项的信息")
        String errorMsg();

        @Field(key = "showMsg", name = "显示的错误信息", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        @FieldEg(eg = "系统内部错误", desc = "条件满足后执行会报错，返回的 showMsg 为该配置项的信息")
        String showMsg();
    }
}
