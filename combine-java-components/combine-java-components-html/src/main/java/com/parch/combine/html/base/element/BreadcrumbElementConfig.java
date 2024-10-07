package com.parch.combine.html.base.element;

import com.parch.combine.html.base.template.BreadcrumbElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "breadcrumb", name = "面包屑元素", templateClass = BreadcrumbElementTemplateLogicConfig.class)
public class BreadcrumbElementConfig extends ElementConfig<BreadcrumbElementTemplateLogicConfig> {

    public BreadcrumbElementConfig() {
        super(SystemElementPathTool.buildJsPath("breadcrumb"), SystemElementPathTool.buildCssPath("breadcrumb"),
                SystemElementPathTool.buildTemplatePath("breadcrumb"), BreadcrumbElementTemplateLogicConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }
}
