package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.PanelElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.panel.register", order = 300, name = "面板元素模板配置注册组件", logicConfigClass = PanelElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PanelTemplateComponent extends AbstractTemplateComponent<PanelElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public PanelTemplateComponent() {
        super(PanelElementTemplateLogicConfig.class, "SYSTEM.PANEL");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
