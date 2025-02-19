package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.WindowElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.window.register", order = 400, name = "窗口元素模板配置注册组件", logicConfigClass = WindowElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class WindowElementComponent extends AbstractElementComponent<WindowElementLogicConfig> {

    /**
     * 构造器
     */
    public WindowElementComponent() {
        super(WindowElementLogicConfig.class, "SYSTEM.WINDOW");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
