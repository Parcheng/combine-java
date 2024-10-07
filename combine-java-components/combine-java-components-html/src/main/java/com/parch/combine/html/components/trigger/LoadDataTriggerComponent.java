package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.dataload.ApiDataLoadLogicConfig;
import com.parch.combine.html.base.trigger.AbstractTriggerComponent;
import com.parch.combine.html.base.trigger.CallFlowTriggerLogicConfig;
import com.parch.combine.html.base.trigger.LoadDataTriggerLogicConfig;
import com.parch.combine.html.base.trigger.TriggerConfig;
import com.parch.combine.html.base.trigger.TriggerTypeEnum;

@Component(key = "trigger.loadData.register", order = 200, name = "加载数据触发配置注册组件", logicConfigClass = LoadDataTriggerLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class LoadDataTriggerComponent extends AbstractTriggerComponent<LoadDataTriggerLogicConfig> {

    /**
     * 构造器
     */
    public LoadDataTriggerComponent() {
        super(LoadDataTriggerLogicConfig.class, TriggerTypeEnum.LOAD_DATA);
    }


    @Override
    protected TriggerConfig getConfig() {
        return getLogicConfig().config();
    }
}
