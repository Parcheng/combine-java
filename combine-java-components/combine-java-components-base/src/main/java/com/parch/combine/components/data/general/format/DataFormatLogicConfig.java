package com.parch.combine.components.data.general.format;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface DataFormatLogicConfig extends ILogicConfig {

    @Field(key = "replace", name = "是否替换源数据", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean replace();

    @Field(key = "items", name = "过滤配置集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataFormatItem.class)
    @FieldDesc("创建配置项集合")
    @FieldEg(eg = "$r.data001.extInfo JSON TO_JSON", desc = "将组件 data001 的 extInfo 字段，转换成 JSON 字符串")
    DataFormatItem[] items();

    interface DataFormatItem {

        @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION, parseExpression = false)
        String source();

        @Field(key = "function", name = "函数", type = FieldTypeEnum.SELECT)
        @FieldSelect(enumClass = DataFormatFunctionEnum.class)
        String function();

        @Field(key = "params", name = "函数的参数", type = FieldTypeEnum.TEXT, isArray = true)
        String[] params();
    }
}
