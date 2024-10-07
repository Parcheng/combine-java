package com.parch.combine.html.base.trigger.common;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.ConfigErrorEnum;

public abstract class AbstractTriggerComponent<L extends ILogicConfig> extends AbstractComponent<IInvalidInitConfig, L> {

    protected TriggerTypeEnum type;

    /**
     * 构造器
     */
    public AbstractTriggerComponent(Class<L> logicClass, TriggerTypeEnum type) {
        super(IInvalidInitConfig.class, logicClass);
        this.type = type;
    }

    @Override
    protected ComponentDataResult execute() {
        L logicConfig = getLogicConfig();
        TriggerConfig config = getConfig();
        if (config == null) {
            return ComponentDataResult.fail(ConfigErrorEnum.CONFIG_IS_NULL);
        }

        TriggerConfigCache.INSTANCE.register(logicConfig.id(), type, config);
        return ComponentDataResult.success(true);
    }

    protected abstract TriggerConfig getConfig();
}
