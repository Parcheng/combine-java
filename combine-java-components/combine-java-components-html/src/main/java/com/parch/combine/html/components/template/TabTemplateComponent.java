package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.AbstractTemplateComponent;
import com.parch.combine.html.base.template.TabElementTemplateLogicConfig;
import com.parch.combine.html.base.template.ElementTemplateConfig;

@Component(key = "template.tab.register", order = 300, name = "页签元素模板配置注册组件", logicConfigClass = TabElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TabTemplateComponent extends AbstractTemplateComponent<TabElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public TabTemplateComponent() {
        super(TabElementTemplateLogicConfig.class, "SYSTEM.TAB");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
