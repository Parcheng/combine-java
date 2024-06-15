package com.parch.combine.components.data.text.split;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

/**
 * 逻辑配置类
 */
public interface DataTextSplitLogicConfig extends ILogicConfig {

    @Field(key = "isReplace", name = "是否替换掉源数据", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean isReplace();

    @Field(key = "source", name = "数据源", type = FieldTypeEnum.EXPRESSION, parseExpression = false, isRequired = true)
    @FieldDesc("注意：非文本类型会先转成JSON再处理")
    String source();

    @Field(key = "regex", name = "拆分的正则表达式", type = FieldTypeEnum.TEXT, isRequired = true)
    String regex();
}
