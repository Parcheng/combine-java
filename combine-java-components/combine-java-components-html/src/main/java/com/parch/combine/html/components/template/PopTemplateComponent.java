package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.PopElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.pop.register", order = 300, name = "泡泡窗元素模板配置注册组件", logicConfigClass = PopElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PopTemplateComponent extends AbstractTemplateComponent<PopElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public PopTemplateComponent() {
        super(PopElementTemplateLogicConfig.class, "SYSTEM.POP");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
