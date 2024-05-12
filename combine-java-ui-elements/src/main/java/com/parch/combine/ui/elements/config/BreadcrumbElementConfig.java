package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

@PageElement(key = "breadcrumb", name = "面包屑元素", templateClass = BreadcrumbElementTemplateConfig.class)
public class BreadcrumbElementConfig extends ElementConfig<BreadcrumbElementTemplateConfig> {

    public BreadcrumbElementConfig() {
        super(SystemElementPathTool.buildJsPath("breadcrumb"), SystemElementPathTool.buildTemplatePath("breadcrumb"), BreadcrumbElementTemplateConfig.class);
    }
}
