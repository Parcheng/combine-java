package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.dataload.ApiDataLoadLogicConfig;
import com.parch.combine.html.base.trigger.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CallFlowTriggerLogicConfig;
import com.parch.combine.html.base.trigger.LoadTriggerLogicConfig;
import com.parch.combine.html.base.trigger.TriggerConfig;
import com.parch.combine.html.base.trigger.TriggerTypeEnum;

@Component(key = "trigger.load.register", order = 200, name = "加载页面元素触发配置注册组件", logicConfigClass = LoadTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class LoadTriggerComponent extends AbstractTriggerComponent<LoadTriggerLogicConfig> {

    /**
     * 构造器
     */
    public LoadTriggerComponent() {
        super(LoadTriggerLogicConfig.class, TriggerTypeEnum.LOAD);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
