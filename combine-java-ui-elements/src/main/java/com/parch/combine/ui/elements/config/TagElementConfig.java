package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

@PageElement(key = "tag", name = "标签元素", templateClass = TagElementTemplateConfig.class)
public class TagElementConfig extends ElementConfig<TagElementTemplateConfig> {

    @Field(key = "type", name = "样式类型（可选值取决于模板配置）", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("系统内置模板支持的类型：normal | success | info | primary | warn | error")
    private String type;

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    public TagElementConfig() {
        super(SystemElementPathTool.buildJsPath("tag"), SystemElementPathTool.buildTemplatePath("tag"), TagElementTemplateConfig.class);
    }

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
}
