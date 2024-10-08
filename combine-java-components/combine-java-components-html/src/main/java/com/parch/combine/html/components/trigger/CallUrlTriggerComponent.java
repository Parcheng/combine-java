package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.trigger.core.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CallUrlTriggerLogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;
import com.parch.combine.html.base.trigger.core.TriggerTypeEnum;

@Component(key = "trigger.callUrl.register", order = 200, name = "调用URL触发配置注册组件", logicConfigClass = CallUrlTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class CallUrlTriggerComponent extends AbstractTriggerComponent<CallUrlTriggerLogicConfig> {

    /**
     * 构造器
     */
    public CallUrlTriggerComponent() {
        super(CallUrlTriggerLogicConfig.class, TriggerTypeEnum.CALL_URL);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
