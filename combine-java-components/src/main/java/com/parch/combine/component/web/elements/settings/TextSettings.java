package com.parch.combine.component.web.elements.settings;

import java.util.List;

/**
 * 页面元素设置
 */
public class TextSettings extends BaseSettings{

    private Integer retract;

    private List<TextLineSettings> lines;

    private List<String> children;

    private String defaultText;

    public static class TextLineSettings{
        private String title;
        private String data;
        private String text;
        private String separator;
        private TextSettings children;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getSeparator() {
            return separator;
        }

        public void setSeparator(String separator) {
            this.separator = separator;
        }

        public TextSettings getChildren() {
            return children;
        }

        public void setChildren(TextSettings children) {
            this.children = children;
        }
    }

    public List<TextLineSettings> getLines() {
        return lines;
    }

    public void setLines(List<TextLineSettings> lines) {
        this.lines = lines;
    }

    public Integer getRetract() {
        return retract;
    }

    public void setRetract(Integer retract) {
        this.retract = retract;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }
}
