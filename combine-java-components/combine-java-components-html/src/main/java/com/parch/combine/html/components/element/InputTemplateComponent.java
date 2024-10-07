package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.InputElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.input.register", order = 400, name = "输入框元素模板配置注册组件", logicConfigClass = InputElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class InputTemplateComponent extends AbstractElementComponent<InputElementLogicConfig> {

    /**
     * 构造器
     */
    public InputTemplateComponent() {
        super(InputElementLogicConfig.class, "SYSTEM.INPUT");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
