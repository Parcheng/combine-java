package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class LineSettings extends BaseSettings{

    @Field(key = "text", name = "线中间的文本", type = FieldTypeEnum.TEXT)
    @FieldDesc("不设置文本，则显示为单线")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
