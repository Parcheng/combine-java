package com.parch.combine.component.web.elements.settings;

/**
 * 页面元素设置
 */
public class PopSettings extends BaseSettings{

    private String type;

    private String text;

    private Boolean hasClose = false;

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

    public Boolean getHasClose() {
        return hasClose;
    }

    public void setHasClose(Boolean hasClose) {
        this.hasClose = hasClose;
    }
}
