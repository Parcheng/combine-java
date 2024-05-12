package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "content", name = "内容元素", templateClass = ContentElementTemplateConfig.class)
public class ContentElementConfig extends ElementConfig<ContentElementTemplateConfig> {

    @Field(key = "leftImg", name = "左图片地址", type = FieldTypeEnum.TEXT)
    private String leftImg;

    @Field(key = "rightImg", name = "右图片地址", type = FieldTypeEnum.TEXT)
    private String rightImg;

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    private String title;

    @Field(key = "text", name = "每一行内容文本", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> text;

    @Field(key = "children", name = "子本文", type = FieldTypeEnum.TEXT)
    private String children;

    public ContentElementConfig() {
        super(SystemElementPathTool.buildJsPath("content"), SystemElementPathTool.buildTemplatePath("content"), ContentElementTemplateConfig.class);
    }

    public String getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(String leftImg) {
        this.leftImg = leftImg;
    }

    public String getRightImg() {
        return rightImg;
    }

    public void setRightImg(String rightImg) {
        this.rightImg = rightImg;
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

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
