package com.parch.combine.page.elements.settings;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class TagSettings extends BaseSettings{

    @ComponentField(key = "type", name = "样式类型（可选值取决于模板配置）", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldDesc("系统内置模板支持的类型：normal | success | info | primary | warn | error")
    private String type;

    @ComponentField(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
