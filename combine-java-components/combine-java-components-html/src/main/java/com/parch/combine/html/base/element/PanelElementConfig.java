package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.PanelElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.common.SubElementConfig;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "panel", name = "面板元素", templateClass = PanelElementTemplateLogicConfig.class)
public class PanelElementConfig extends ElementConfig<PanelElementTemplateLogicConfig> {

    @Field(key = "title", name = "标题配置", type = FieldTypeEnum.TEXT)
    private String title;

    @Field(key = "body", name = "页签内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(SubElementConfig.class)
    private SubElementConfig body;

    public PanelElementConfig() {
        super(SystemElementPathTool.buildJsPath("panel"), SystemElementPathTool.buildCssPath("panel"),
                SystemElementPathTool.buildTemplatePath("panel"), PanelElementTemplateLogicConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubElementConfig getBody() {
        return body;
    }

    public void setBody(SubElementConfig body) {
        this.body = body;
    }
}
