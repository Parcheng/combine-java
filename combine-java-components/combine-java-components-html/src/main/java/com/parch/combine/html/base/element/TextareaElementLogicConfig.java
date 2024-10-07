package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface TextareaElementLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "多行文本KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    String key();

    @Field(key = "value", name = "多行文本内容", type = FieldTypeEnum.TEXT)
    String value();
}
