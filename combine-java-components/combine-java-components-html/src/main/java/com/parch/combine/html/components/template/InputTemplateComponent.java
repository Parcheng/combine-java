package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.AbstractTemplateComponent;
import com.parch.combine.html.base.template.InputElementTemplateLogicConfig;
import com.parch.combine.html.base.template.ElementTemplateConfig;

@Component(key = "template.input.register", order = 300, name = "输入框元素模板配置注册组件", logicConfigClass = InputElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class InputTemplateComponent extends AbstractTemplateComponent<InputElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public InputTemplateComponent() {
        super(InputElementTemplateLogicConfig.class, "SYSTEM.INPUT");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
