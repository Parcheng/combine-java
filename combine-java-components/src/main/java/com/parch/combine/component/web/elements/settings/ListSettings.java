package com.parch.combine.component.web.elements.settings;

/**
 * 页面元素设置
 */
public class ListSettings extends BaseSettings{

    private SubElementSettings content;

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
