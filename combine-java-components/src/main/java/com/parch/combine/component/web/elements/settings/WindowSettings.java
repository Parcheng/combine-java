package com.parch.combine.component.web.elements.settings;

/**
 * 页面元素设置
 */
public class WindowSettings extends BaseSettings{

    private String title;

    private SubElementSettings body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubElementSettings getBody() {
        return body;
    }

    public void setBody(SubElementSettings body) {
        this.body = body;
    }
}
