package com.parch.combine.html.old.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.element.PanelElementLogicConfig;
import com.parch.combine.html.old.base.element.core.AbstractElementComponent;
import com.parch.combine.html.old.base.element.core.ElementConfig;

@Component(key = "element.panel.register", order = 400, name = "面板元素模板配置注册组件", logicConfigClass = PanelElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PanelElementComponent extends AbstractElementComponent<PanelElementLogicConfig> {

    /**
     * 构造器
     */
    public PanelElementComponent() {
        super(PanelElementLogicConfig.class, "SYSTEM.PANEL");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
