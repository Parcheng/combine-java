package com.parch.combine.components.data.text.replace;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface DataTextReplaceLogicConfig extends ILogicConfig {

    @Field(key = "isReplace", name = "是否替换掉源数据", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    default Boolean isReplace() {
        return true;
    }

    @Field(key = "mode", name = "替换方式", type = FieldTypeEnum.SELECT, defaultValue = "ALL")
    @FieldSelect(enumClass = DataTextReplaceModeEnum.class)
    default String mode() {
        return DataTextReplaceModeEnum.ALL.name();
    }

    @Field(key = "source", name = "数据源", type = FieldTypeEnum.EXPRESSION, parseExpression = false, isRequired = true)
    @FieldDesc("支持结构对象、集合和文本类型")
    String source();

    @Field(key = "oldText", name = "旧文本", type = FieldTypeEnum.TEXT, isRequired = true)
    String oldText();

    @Field(key = "newText", name = "新文本", type = FieldTypeEnum.TEXT, isRequired = true)
    String newText();
}
