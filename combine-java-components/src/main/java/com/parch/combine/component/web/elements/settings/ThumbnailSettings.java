package com.parch.combine.component.web.elements.settings;

import java.util.List;

/**
 * 页面元素设置
 */
public class ThumbnailSettings extends BaseSettings{

    private String path;

    private String title;

    private List<String> text;

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
