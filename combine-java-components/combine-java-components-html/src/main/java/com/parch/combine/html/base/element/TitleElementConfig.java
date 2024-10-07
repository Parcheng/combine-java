package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.TitleElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "title", name = "标题元素", templateClass = TitleElementTemplateLogicConfig.class)
public class TitleElementConfig extends ElementConfig<TitleElementTemplateLogicConfig> {

    @Field(key = "text", name = "文本", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    @Field(key = "level", name = "级别（1-6）", type = FieldTypeEnum.NUMBER, isRequired = true)
    private Integer level;

    @Field(key = "top", name = "是否需要上面添加空行", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean top;

    @Field(key = "bottom", name = "是否需要下分割线", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean bottom;

    public TitleElementConfig() {
        super(SystemElementPathTool.buildJsPath("title"), SystemElementPathTool.buildCssPath("title"),
                SystemElementPathTool.buildTemplatePath("title"), TitleElementTemplateLogicConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

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
