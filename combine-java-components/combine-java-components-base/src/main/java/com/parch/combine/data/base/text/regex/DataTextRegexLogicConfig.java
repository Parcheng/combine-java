package com.parch.combine.data.base.text.regex;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface DataTextRegexLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    @FieldDesc("提示：非文本类型会转为JSON格式处理")
    Object source();

    @Field(key = "regex", name = "正则表达式", type = FieldTypeEnum.TEXT)
    String regex();

    @Field(key = "resultMode", name = "返回结果的方式", type = FieldTypeEnum.SELECT, defaultValue = "FULL")
    @FieldSelect(enumClass = DataTextRegexResultModeEnum.class)
    String resultMode();
}
