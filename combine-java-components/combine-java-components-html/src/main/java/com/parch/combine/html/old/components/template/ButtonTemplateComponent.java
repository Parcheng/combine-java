package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.ButtonElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.button.register", order = 300, name = "按钮元素模板配置注册组件", logicConfigClass = ButtonElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ButtonTemplateComponent extends AbstractTemplateComponent<ButtonElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public ButtonTemplateComponent() {
        super(ButtonElementTemplateLogicConfig.class, "SYSTEM.BUTTON");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
