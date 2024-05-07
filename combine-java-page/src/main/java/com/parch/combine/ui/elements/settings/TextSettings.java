package com.parch.combine.ui.elements.settings;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class TextSettings extends BaseSettings{

    @Field(key = "retract", name = "缩进数", type = FieldTypeEnum.NUMBER)
    private Integer retract;

    @Field(key = "lines", name = "行数据配置", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    @FieldObject(type = TextLineSettings.class)
    private List<TextLineSettings> lines;

    @Field(key = "children", name = "子文本数据配置", type = FieldTypeEnum.TEXT)
    private List<String> children;

    @Field(key = "defaultText", name = "所有文本为空时默认显示文本", type = FieldTypeEnum.TEXT)
    private String defaultText;

    public static class TextLineSettings{

        @Field(key = "title", name = "行标题配置", type = FieldTypeEnum.TEXT)
        private String title;

        @Field(key = "data", name = "行数据二次取值", type = FieldTypeEnum.TEXT)
        private String data;

        @Field(key = "text", name = "行文本", type = FieldTypeEnum.TEXT)
        private String text;

        @Field(key = "separator", name = "行数据为多条文本时的分隔符", type = FieldTypeEnum.TEXT, defaultValue = "<br/>")
        private String separator;

        @Field(key = "children", name = "子文本数据配置，配置项同该级相同", type = FieldTypeEnum.OBJECT)
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
