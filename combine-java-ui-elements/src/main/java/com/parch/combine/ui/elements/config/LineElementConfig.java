package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "line", name = "分割线元素", templateClass = LineElementTemplateConfig.class)
public class LineElementConfig extends ElementConfig<LineElementTemplateConfig> {

    @Field(key = "text", name = "线中间的文本", type = FieldTypeEnum.TEXT)
    @FieldDesc("不设置文本，则显示为单线")
    private String text;

    public LineElementConfig() {
        super(SystemElementPathTool.buildJsPath("line"), SystemElementPathTool.buildTemplatePath("line"), LineElementTemplateConfig.class);
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
}
