package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.NavBarElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.navBar.register", order = 300, name = "导航条元素模板配置注册组件", logicConfigClass = NavBarElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class NavBarTemplateComponent extends AbstractTemplateComponent<NavBarElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public NavBarTemplateComponent() {
        super(NavBarElementTemplateLogicConfig.class, "SYSTEM.NAV_BAR");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
