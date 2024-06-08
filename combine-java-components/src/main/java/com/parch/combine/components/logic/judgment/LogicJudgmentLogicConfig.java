package com.parch.combine.components.logic.judgment;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

public interface LogicJudgmentLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(LogicJudgmentItem.class)
    LogicJudgmentItem[] items();

    interface LogicJudgmentItem {

        @Field(key = "compare", name = "比较条件配置", type = FieldTypeEnum.OBJECT)
        @FieldObject(CompareGroupConfig.class)
        CompareGroupConfig compare();

        @Field(key = "components", name = "要执行的逻辑，可以是组件ID，也可以是组件配置", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
        @FieldEg(eg = "[\"logicJudgmentTestData\", { \"type\": \"XXX\", ... }", desc = "条件满足后，先执行 ID 为 logicJudgmentTestData 的组件配置，再执行第二项类型为 XXX 的组件配置逻辑")
        String[] components();
    }
}
