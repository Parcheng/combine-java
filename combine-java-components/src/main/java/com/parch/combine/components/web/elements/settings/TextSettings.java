package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class TextSettings extends BaseSettings{

    @ComponentField(key = "retract", name = "缩进数", type = FieldTypeEnum.NUMBER)
    private Integer retract;

    @ComponentField(key = "lines", name = "行数据配置", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    @ComponentFieldObject(type = TextLineSettings.class)
    private List<TextLineSettings> lines;

    @ComponentField(key = "children", name = "子文本数据配置", type = FieldTypeEnum.TEXT)
    private List<String> children;

    @ComponentField(key = "defaultText", name = "所有文本为空时默认显示文本", type = FieldTypeEnum.TEXT)
    private String defaultText;

    public static class TextLineSettings{

        @ComponentField(key = "title", name = "行标题配置", type = FieldTypeEnum.TEXT)
        private String title;

        @ComponentField(key = "data", name = "行数据二次取值", type = FieldTypeEnum.TEXT)
        private String data;

        @ComponentField(key = "text", name = "行文本", type = FieldTypeEnum.TEXT)
        private String text;

        @ComponentField(key = "separator", name = "行数据为多条文本时的分隔符", type = FieldTypeEnum.TEXT, defaultValue = "<br/>")
        private String separator;

        @ComponentField(key = "children", name = "子文本数据配置，配置项同该级相同", type = FieldTypeEnum.OBJECT)
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
