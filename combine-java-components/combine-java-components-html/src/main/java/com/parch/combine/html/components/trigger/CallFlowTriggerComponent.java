package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.trigger.core.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CallFlowTriggerLogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;
import com.parch.combine.html.common.enums.TriggerTypeEnum;

@Component(key = "trigger.callFlow.register", order = 200, name = "调用流程触发配置注册组件", logicConfigClass = CallFlowTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class CallFlowTriggerComponent extends AbstractTriggerComponent<CallFlowTriggerLogicConfig> {

    /**
     * 构造器
     */
    public CallFlowTriggerComponent() {
        super(CallFlowTriggerLogicConfig.class, TriggerTypeEnum.CALL_FLOW);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
