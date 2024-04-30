package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class TitleSettings extends BaseSettings{

    @ComponentField(key = "text", name = "文本", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    @ComponentField(key = "level", name = "级别（1-6）", type = FieldTypeEnum.NUMBER, isRequired = true)
    private Integer level;

    @ComponentField(key = "top", name = "是否需要上面添加空行", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean top;

    @ComponentField(key = "bottom", name = "是否需要下分割线", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean bottom;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Boolean getBottom() {
        return bottom;
    }

    public void setBottom(Boolean bottom) {
        this.bottom = bottom;
    }
}
