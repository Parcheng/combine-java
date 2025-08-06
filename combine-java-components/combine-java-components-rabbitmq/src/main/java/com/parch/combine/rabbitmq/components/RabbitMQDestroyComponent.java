package com.parch.combine.rabbitmq.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.rabbitmq.base.destroy.RabbitMQDestroyLogicConfig;
import com.parch.combine.rabbitmq.base.helper.RabbitMQHelper;

@Component(key = "destroy", name = "RabbitMQ销毁连接缓存组件", logicConfigClass = RabbitMQDestroyLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true")
public class RabbitMQDestroyComponent extends AbstractComponent<IInvalidInitConfig, RabbitMQDestroyLogicConfig> {

    public RabbitMQDestroyComponent() {
        super(IInvalidInitConfig.class, RabbitMQDestroyLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        RabbitMQHelper.destroy(getScopeKey());
        return ComponentDataResult.success(true);
    }
}
