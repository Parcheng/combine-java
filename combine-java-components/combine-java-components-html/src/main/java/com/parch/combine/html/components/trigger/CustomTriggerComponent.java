package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.trigger.core.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CustomTriggerLogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;
import com.parch.combine.html.base.trigger.core.TriggerTypeEnum;

@Component(key = "trigger.custom.register", order = 200, name = "自定义触发配置注册组件", logicConfigClass = CustomTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class CustomTriggerComponent extends AbstractTriggerComponent<CustomTriggerLogicConfig> {

    /**
     * 构造器
     */
    public CustomTriggerComponent() {
        super(CustomTriggerLogicConfig.class, TriggerTypeEnum.CUSTOM);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
