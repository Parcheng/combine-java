package com.parch.combine.components.data.general.reset;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

public interface DataResetLogicConfig extends ILogicConfig {

    @Field(key = "nullValue", name = "是否允许空值", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    default Boolean nullValue() {
        return true;
    }

    @Field(key = "items", name = "重新设值配置集合，", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataResetCompare.class)
    DataResetCompare[] items();

    interface DataResetCompare {

        @Field(key = "compare", name = "赋值条件", type = FieldTypeEnum.OBJECT)
        @FieldObject(CompareGroupConfig.class)
        CompareGroupConfig compare();

        @Field(key = "resets", name = "赋值配置项集合", type = FieldTypeEnum.CONFIG)
        @FieldObject(DataResetConfig.class)
        @FieldDesc("将 “新值” 赋值给 “要重新赋值的字段”")
        @FieldEg(eg = "{\"target\":\"$r.data001.name\",\"type\":\"STRING\",\"value\":\"zhangsan\"}", desc = "将 zhangsan 重新赋值给组件 data001 的 name 字段")
        @FieldEg(eg = "{\"target\":\"$r.data001.age\",\"type\":\"INT\",\"value\":\"1\"}", desc = "将 1 重新赋值给组件 data001 的 age 字段")
        @FieldEg(eg = "{\"target\":\"$r.data001.desc\",\"type\":\"VARIABLE\",\"value\":\"$r.data002.desc\"}", desc = "将组件 data002 的 desc 字段赋值给组件 data001 的 desc 字段")
        DataResetConfig[] resets();
    }

    interface DataResetConfig {

        @Field(key = "target", name = "目标数据", type = FieldTypeEnum.EXPRESSION, isRequired = true, parseExpression = false)
        String target();

        @Field(key = "type", name = "数据类型", type = FieldTypeEnum.SELECT, isRequired = true)
        String type();

        @Field(key = "value", name = "新值", type = FieldTypeEnum.ANY, isRequired = true)
        Object value();
    }
}
