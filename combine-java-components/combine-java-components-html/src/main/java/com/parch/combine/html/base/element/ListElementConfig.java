package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.ListElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.common.SubElementConfig;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "list", name = "列表元素", templateClass = ListElementTemplateLogicConfig.class)
public class ListElementConfig extends ElementConfig<ListElementTemplateLogicConfig> {

    @Field(key = "content", name = "列表项内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(SubElementConfig.class)
    private SubElementConfig content;

    @Field(key = "defaultText", name = "列表为空时默认显示文本", type = FieldTypeEnum.TEXT)
    private String defaultText;

    public ListElementConfig() {
        super(SystemElementPathTool.buildJsPath("list"), SystemElementPathTool.buildCssPath("list"),
                SystemElementPathTool.buildTemplatePath("list"), ListElementTemplateLogicConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public SubElementConfig getContent() {
        return content;
    }

    public void setContent(SubElementConfig content) {
        this.content = content;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }
}
