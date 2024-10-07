package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.dataload.ApiDataLoadLogicConfig;
import com.parch.combine.html.base.trigger.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CallFlowTriggerLogicConfig;
import com.parch.combine.html.base.trigger.CustomTriggerLogicConfig;
import com.parch.combine.html.base.trigger.TriggerConfig;
import com.parch.combine.html.base.trigger.TriggerTypeEnum;

@Component(key = "trigger.custom.register", name = "自定义触发配置注册组件", logicConfigClass = CustomTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class CustomTriggerComponent extends AbstractTriggerComponent<CallFlowTriggerLogicConfig> {

    /**
     * 构造器
     */
    public CustomTriggerComponent() {
        super(CallFlowTriggerLogicConfig.class, TriggerTypeEnum.CUSTOM);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
