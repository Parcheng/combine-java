package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "tag", name = "标签元素", templateClass = TagElementTemplateConfig.class)
public class TagElementConfig extends ElementConfig<TagElementTemplateConfig> {

    @Field(key = "tagType", name = "样式类型（可选值取决于模板配置）", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("系统内置模板支持的类型：normal | success | info | primary | warn | error")
    private String tagType;

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    public TagElementConfig() {
        super(SystemElementPathTool.buildJsPath("tag"), SystemElementPathTool.buildTemplatePath("tag"), TagElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
