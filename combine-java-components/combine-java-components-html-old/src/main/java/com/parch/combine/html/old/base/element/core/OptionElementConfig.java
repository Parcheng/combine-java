package com.parch.combine.html.old.base.element.core;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface OptionElementConfig {

    @Field(key = "data", name = "选项数据配置", type = FieldTypeEnum.ANY)
    Object data();

    @Field(key = "value", name = "单选项值（字段名）", type = FieldTypeEnum.TEXT, isRequired = true)
    String value();

    @Field(key = "text", name = "单选项显示文本（字段名）", type = FieldTypeEnum.TEXT, isRequired = true)
    String text();
}
