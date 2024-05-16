package com.parch.combine.ui.elements.common;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.SubConfig;

@SubConfig
public class OptionElementConfig {

    @Field(key = "data", name = "选项数据配置", type = FieldTypeEnum.OBJECT)
    private Object data;

    @Field(key = "value", name = "单选项值（字段名）", type = FieldTypeEnum.TEXT, isRequired = true)
    private String value;

    @Field(key = "text", name = "单选项显示文本（字段名）", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
