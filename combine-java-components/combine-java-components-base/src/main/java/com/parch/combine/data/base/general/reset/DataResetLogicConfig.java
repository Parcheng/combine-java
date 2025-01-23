package com.parch.combine.data.base.general.reset;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;
import com.parch.combine.core.component.tools.variable.DataTypeEnum;

public interface DataResetLogicConfig extends ILogicConfig {

    @Field(key = "nullValue", name = "是否允许空值", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean nullValue();

    @Field(key = "items", name = "重新设值配置集合，", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataResetCompare.class)
    DataResetCompare[] items();

    interface DataResetCompare {

        @Field(key = "compare", name = "赋值条件", type = FieldTypeEnum.OBJECT)
        @FieldObject(CompareGroupConfig.class)
        CompareGroupConfig compare();

        @Field(key = "resets", name = "赋值配置项集合", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(DataResetConfig.class)
        @FieldDesc("将 “新值” 赋值给 “要重新赋值的字段”")
        @FieldEg(eg = "{\"target\":\"$r.data001.name\",\"value\":\"zhangsan\"}", desc = "将 zhangsan 重新赋值给组件 data001 的 name 字段")
        @FieldEg(eg = "{\"target\":\"$r.data001.age\",\"value\":1}", desc = "将 1 重新赋值给组件 data001 的 age 字段")
        @FieldEg(eg = "{\"target\":\"$r.data001.desc\",\"value\":\"$r.data002.desc\"}", desc = "将组件 data002 的 desc 字段赋值给组件 data001 的 desc 字段")
        DataResetConfig[] resets();
    }

    interface DataResetConfig {

        @Field(key = "target", name = "目标数据", type = FieldTypeEnum.EXPRESSION, isRequired = true, parseExpression = false)
        String target();

        @Field(key = "value", name = "新值", type = FieldTypeEnum.ANY, isRequired = true)
        Object value();
    }
}
