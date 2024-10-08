package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.trigger.core.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.SkipTriggerLogicConfig;
import com.parch.combine.html.base.trigger.core.TriggerConfig;
import com.parch.combine.html.base.trigger.core.TriggerTypeEnum;

@Component(key = "trigger.skip.register", order = 200, name = "地址跳转触发配置注册组件", logicConfigClass = SkipTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class SkipTriggerComponent extends AbstractTriggerComponent<SkipTriggerLogicConfig> {

    /**
     * 构造器
     */
    public SkipTriggerComponent() {
        super(SkipTriggerLogicConfig.class, TriggerTypeEnum.SKIP);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
