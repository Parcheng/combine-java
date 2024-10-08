package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.NavBarElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.navBar.register", order = 400, name = "导航条元素模板配置注册组件", logicConfigClass = NavBarElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class NavBarTemplateComponent extends AbstractElementComponent<NavBarElementLogicConfig> {

    /**
     * 构造器
     */
    public NavBarTemplateComponent() {
        super(NavBarElementLogicConfig.class, "SYSTEM.NAV_BAR");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
