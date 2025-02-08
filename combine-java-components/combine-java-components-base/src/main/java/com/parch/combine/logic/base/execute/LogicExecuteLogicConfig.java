package com.parch.combine.logic.base.execute;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface LogicExecuteLogicConfig extends ILogicConfig {

    @Field(key = "mappings", name = "数据映射", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(MappingItem.class)
    MappingItem[] mappings();

    @Field(key = "components", name = "要执行的逻辑", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
    String[] components();

    @Field(key = "out", name = "输出结果", type = FieldTypeEnum.ANY, parseExpression = false)
    @FieldDesc("默认为 components 中最后一个组件的执行结果")
    Object out();

    interface MappingItem {

        @Field(key = "value", name = "值", type = FieldTypeEnum.ANY, parseExpression = false, isRequired = true)
        Object value();

        @Field(key = "target", name = "值写入目标", type = FieldTypeEnum.EXPRESSION, parseExpression = false, isRequired = true)
        String target();
    }
}
