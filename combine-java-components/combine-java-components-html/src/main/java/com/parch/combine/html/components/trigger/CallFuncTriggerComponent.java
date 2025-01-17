package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.trigger.core.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CallFuncTriggerLogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;
import com.parch.combine.html.common.enums.TriggerTypeEnum;

@Component(key = "trigger.callFunc.register", order = 200, name = "调用函数触发配置注册组件", logicConfigClass = CallFuncTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class CallFuncTriggerComponent extends AbstractTriggerComponent<CallFuncTriggerLogicConfig> {

    /**
     * 构造器
     */
    public CallFuncTriggerComponent() {
        super(CallFuncTriggerLogicConfig.class, TriggerTypeEnum.CALL_FUNC);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
