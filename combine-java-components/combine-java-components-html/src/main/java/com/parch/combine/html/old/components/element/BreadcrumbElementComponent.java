package com.parch.combine.html.old.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.element.BreadcrumbElementLogicConfig;
import com.parch.combine.html.old.base.element.core.AbstractElementComponent;
import com.parch.combine.html.old.base.element.core.ElementConfig;

@Component(key = "element.breadcrumb.register", order = 400, name = "面包屑元素模板配置注册组件", logicConfigClass = BreadcrumbElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class BreadcrumbElementComponent extends AbstractElementComponent<BreadcrumbElementLogicConfig> {

    /**
     * 构造器
     */
    public BreadcrumbElementComponent() {
        super(BreadcrumbElementLogicConfig.class, "SYSTEM.BREADCRUMB");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
