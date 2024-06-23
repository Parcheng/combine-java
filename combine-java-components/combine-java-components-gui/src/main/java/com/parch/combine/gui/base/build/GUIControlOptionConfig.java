package com.parch.combine.gui.base.build;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class GUIControlOptionConfig {

    @Field(key = "text", name = "文本", type = FieldTypeEnum.TEXT)
    private String text;

    @Field(key = "value", name = "值", type = FieldTypeEnum.TEXT, isRequired = true)
    private String value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
