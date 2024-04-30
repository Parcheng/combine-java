package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class AudioSettings extends BaseSettings{

    @ComponentField(key = "src", name = "音频来源", type = FieldTypeEnum.TEXT, isRequired = true)
    private String src;

    @ComponentField(key = "text", name = "不兼容时显示的文本提示信息", type = FieldTypeEnum.TEXT)
    private String text;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
