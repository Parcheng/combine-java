package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "breadcrumb", name = "面包屑元素", templateClass = BreadcrumbElementTemplateConfig.class)
public class BreadcrumbElementConfig extends ElementConfig<BreadcrumbElementTemplateConfig> {

    public BreadcrumbElementConfig() {
        super(SystemElementPathTool.buildJsPath("breadcrumb"), SystemElementPathTool.buildCssPath("breadcrumb"),
                SystemElementPathTool.buildTemplatePath("breadcrumb"), BreadcrumbElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }
}
