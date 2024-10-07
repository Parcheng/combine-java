package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.RadioElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.radio.register", order = 300, name = "单选框元素模板配置注册组件", logicConfigClass = RadioElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class RadioTemplateComponent extends AbstractTemplateComponent<RadioElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public RadioTemplateComponent() {
        super(RadioElementTemplateLogicConfig.class, "SYSTEM.RADIO");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
