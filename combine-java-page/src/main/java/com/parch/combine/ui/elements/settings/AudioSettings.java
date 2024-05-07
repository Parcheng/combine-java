package com.parch.combine.ui.elements.settings;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.elements.BaseSettings;

/**
 * 页面元素设置
 */
public class AudioSettings extends BaseSettings {

    @Field(key = "src", name = "音频来源", type = FieldTypeEnum.TEXT, isRequired = true)
    private String src;

    @Field(key = "text", name = "不兼容时显示的文本提示信息", type = FieldTypeEnum.TEXT)
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
