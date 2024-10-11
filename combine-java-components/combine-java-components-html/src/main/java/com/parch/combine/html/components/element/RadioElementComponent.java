package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.RadioElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.radio.register", order = 400, name = "单选框元素模板配置注册组件", logicConfigClass = RadioElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class RadioElementComponent extends AbstractElementComponent<RadioElementLogicConfig> {

    /**
     * 构造器
     */
    public RadioElementComponent() {
        super(RadioElementLogicConfig.class, "SYSTEM.RADIO");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
