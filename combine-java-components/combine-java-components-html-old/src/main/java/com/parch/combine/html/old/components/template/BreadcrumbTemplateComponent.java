package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.BreadcrumbElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.breadcrumb.register", order = 300, name = "面包屑元素模板配置注册组件", logicConfigClass = BreadcrumbElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class BreadcrumbTemplateComponent extends AbstractTemplateComponent<BreadcrumbElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public BreadcrumbTemplateComponent() {
        super(BreadcrumbElementTemplateLogicConfig.class, "SYSTEM.BREADCRUMB");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
