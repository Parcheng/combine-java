package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.dataload.ApiDataLoadLogicConfig;
import com.parch.combine.html.base.trigger.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CallFlowTriggerLogicConfig;
import com.parch.combine.html.base.trigger.TriggerConfig;
import com.parch.combine.html.base.trigger.TriggerTypeEnum;

@Component(key = "trigger.callFlow.register", name = "调用流程触发配置注册组件", logicConfigClass = ApiDataLoadLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
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
