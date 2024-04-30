package com.parch.combine.component.web.elements.settings;

/**
 * 页面元素设置
 */
public class TitleSettings extends BaseSettings{

    private String text;

    private Integer level;

    private Boolean top;

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
