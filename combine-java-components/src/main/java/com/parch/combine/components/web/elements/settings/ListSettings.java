package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class ListSettings extends BaseSettings{

    @Field(key = "content", name = "列表项内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(type = SubElementSettings.class)
    private SubElementSettings content;

    @Field(key = "defaultText", name = "列表为空时默认显示文本", type = FieldTypeEnum.TEXT)
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
