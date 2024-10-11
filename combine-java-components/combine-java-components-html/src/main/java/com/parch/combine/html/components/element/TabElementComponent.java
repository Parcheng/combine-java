package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.TabElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.tab.register", order = 400, name = "页签元素模板配置注册组件", logicConfigClass = TabElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TabElementComponent extends AbstractElementComponent<TabElementLogicConfig> {

    /**
     * 构造器
     */
    public TabElementComponent() {
        super(TabElementLogicConfig.class, "SYSTEM.TAB");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
