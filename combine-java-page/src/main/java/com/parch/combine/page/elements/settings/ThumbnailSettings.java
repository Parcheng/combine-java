package com.parch.combine.page.elements.settings;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class ThumbnailSettings extends BaseSettings{

    @ComponentField(key = "path", name = "图片路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String path;

    @ComponentField(key = "title", name = "内容标题", type = FieldTypeEnum.TEXT)
    private String title;

    @ComponentField(key = "text", name = "内容的每行文本", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> text;

    @ComponentField(key = "buttons", name = "操作按钮配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldObject(type = ButtonSettings.ButtonItemSettings.class)
    private List<ButtonSettings.ButtonItemSettings> buttons;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<ButtonSettings.ButtonItemSettings> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonSettings.ButtonItemSettings> buttons) {
        this.buttons = buttons;
    }
}
