package com.parch.combine.components.data.general.filter;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface DataFilterLogicConfig extends ILogicConfig {

    @Field(key = "resultId", name = "其他组件ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("如果指定了该参数，则会将 resultId 对应组件的执行结果作为该组件的执行结果返回，如果未指定则默认使用上一步组件的结果")
    String resultId();

    @Field(key = "items", name = "过滤配置集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataFilterItem.class)
    @FieldDesc("创建配置项集合")
    @FieldEg(eg = "{\"target\":\"$r.data001.id\"}", desc = "将 data001 组件返回结果的 id 字段清除掉")
    @FieldEg(eg = "{\"target\":\"$r.data001.name\", \"rule\":\"REPLACE\", \"params\":[\"zhangsan\"]}", desc = "将 data001 组件返回结果的 name 字段的值替换为 zhangsan")
    @FieldEg(eg = "{\"target\":\"$r.data001.type\", \"rule\":\"REPLACE\", \"params\":[\"#{$c.type}\"]}", desc = "表示将 data001 组件返回结果的 name 字段的值替换为全局变量的 type 字段值")
    DataFilterItem[] items();

    interface DataFilterItem {

        @Field(key = "target", name = "要过滤的字段的路径", type = FieldTypeEnum.EXPRESSION, parseExpression = false, isRequired = true)
        String target();

        @Field(key = "rule", name = "过滤规则，默认为 CLEAR 规则", type = FieldTypeEnum.TEXT)
        @FieldSelect(enumClass = DataFilterRuleEnum.class)
        String rule();

        @Field(key = "params", name = "过滤规则的参数", type = FieldTypeEnum.TEXT, isArray = true)
        String[] params();
    }
}
