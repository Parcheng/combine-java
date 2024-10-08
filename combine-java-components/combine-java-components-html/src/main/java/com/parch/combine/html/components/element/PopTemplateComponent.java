package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.PopElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.pop.register", order = 400, name = "泡泡窗元素模板配置注册组件", logicConfigClass = PopElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PopTemplateComponent extends AbstractElementComponent<PopElementLogicConfig> {

    /**
     * 构造器
     */
    public PopTemplateComponent() {
        super(PopElementLogicConfig.class, "SYSTEM.POP");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
