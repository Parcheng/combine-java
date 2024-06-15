package com.parch.combine.components.data.general.calc;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface DataCalcLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(DataCalcItem.class)
    @FieldDesc("计算配置项集合")
    @FieldEg(eg = "{\"mode\":\"CALC\", \"params\":[\"100-20*2+(30/2+26%20)-30\"]}", desc = "计算算术表达式")
    @FieldEg(eg = "{\"target\":\"#{$r.data001.min}\", \"mode\":\"MIN\", \"params\":[\"a\",\"1\",\"b\",\"34\"]]}", desc = "将 a 1 b 34 中的最大值写入到组件 data001 的 min 字段中")
    @FieldEg(eg = "{\"target\":\"#{$r.data001.max}\", \"mode\":\"MAX\", \"params\":[\"#{list}\",\"100\"]}", desc = "将 100 和入参的 list 集合所有值中的最大值写入到组件 data001 的 max 字段")
    @FieldEg(eg = "{\"target\":\"#{$r.data001.random}\", \"mode\":\"RANDOM\", \"params\":[\"100000\"]}", desc = "获取 1 - 100000 的随机数写入到将组件 data001 的 random 字段")
    @FieldEg(eg = "{\"target\":\"#{$r.data001.uuid}\", \"mode\":\"UUID\"}", desc = "生成一个 UUID 写入到组件 data001 的 uuid 字段")
    DataCalcItem[] items();

    interface DataCalcItem {

        @Field(key = "target", name = "计算结果要写入的数据源（空表示不写入）", type = FieldTypeEnum.EXPRESSION, parseExpression = false)
        String target();

        @Field(key = "mode", name = "计算函数", type = FieldTypeEnum.SELECT, isRequired = true)
        @FieldSelect(enumClass = DataCalcModeEnum.class)
        String mode();

        @Field(key = "params", name = "函数参数", type = FieldTypeEnum.ANY, isArray = true)
        Object[] params();
    }
}
