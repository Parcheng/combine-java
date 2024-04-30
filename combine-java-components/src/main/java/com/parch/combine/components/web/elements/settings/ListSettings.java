package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class ListSettings extends BaseSettings{

    @ComponentField(key = "content", name = "列表项内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @ComponentFieldObject(type = SubElementSettings.class)
    private SubElementSettings content;

    @ComponentField(key = "defaultText", name = "列表为空时默认显示文本", type = FieldTypeEnum.TEXT)
    private String defaultText;

    public SubElementSettings getContent() {
        return content;
    }

    public void setContent(SubElementSettings content) {
        this.content = content;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }
}
