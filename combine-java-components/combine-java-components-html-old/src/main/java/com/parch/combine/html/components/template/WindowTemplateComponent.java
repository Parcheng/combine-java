package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.WindowElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.window.register", order = 300, name = "窗口元素模板配置注册组件", logicConfigClass = WindowElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class WindowTemplateComponent extends AbstractTemplateComponent<WindowElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public WindowTemplateComponent() {
        super(WindowElementTemplateLogicConfig.class, "SYSTEM.WINDOW");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
