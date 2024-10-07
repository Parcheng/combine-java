package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.ContentElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "content", name = "内容元素", templateClass = ContentElementTemplateLogicConfig.class)
public class ContentElementConfig extends ElementConfig<ContentElementTemplateLogicConfig> {

    @Field(key = "top", name = "顶部图片配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ContentImgConfig.class)
    private ContentImgConfig top;

    @Field(key = "left", name = "左侧图片配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ContentImgConfig.class)
    private ContentImgConfig left;

    @Field(key = "right", name = "右侧图片配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ContentImgConfig.class)
    private ContentImgConfig right;

    @Field(key = "content", name = "内容配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ContentConfig.class)
    private ContentConfig content;

    public ContentElementConfig() {
        super(SystemElementPathTool.buildJsPath("content"), SystemElementPathTool.buildCssPath("content"),
                SystemElementPathTool.buildTemplatePath("content"), ContentElementTemplateLogicConfig.class);
    }

    public static class ContentImgConfig {
        @Field(key = "img", name = "图片地址", type = FieldTypeEnum.TEXT, isRequired = true)
        private String img;

        @Field(key = "size", name = "尺寸（1-100）%", type = FieldTypeEnum.NUMBER, isRequired = true)
        private Integer size;

        @Field(key = "height", name = "高度 px", type = FieldTypeEnum.NUMBER)
        private Integer height;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }
    }

    public static class ContentConfig {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
        private String title;

        @Field(key = "textSize", name = "文本尺寸（1-100）%", type = FieldTypeEnum.NUMBER, isRequired = true)
        private Integer size;

        @Field(key = "text", name = "每一行内容文本", type = FieldTypeEnum.TEXT, isArray = true)
        private List<String> text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public ContentImgConfig getTop() {
        return top;
    }

    public void setTop(ContentImgConfig top) {
        this.top = top;
    }

    public ContentImgConfig getLeft() {
        return left;
    }

    public void setLeft(ContentImgConfig left) {
        this.left = left;
    }

    public ContentImgConfig getRight() {
        return right;
    }

    public void setRight(ContentImgConfig right) {
        this.right = right;
    }

    public ContentConfig getContent() {
        return content;
    }

    public void setContent(ContentConfig content) {
        this.content = content;
    }
}
