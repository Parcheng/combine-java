package com.parch.combine.rabbitmq.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.rabbitmq.base.destroy.RabbitMQDestroyInitConfig;
import com.parch.combine.rabbitmq.base.destroy.RabbitMQDestroyLogicConfig;
import com.parch.combine.rabbitmq.base.helper.RabbitMQHelper;

@Component(key = "destroy", name = "RabbitMQ销毁连接缓存组件", logicConfigClass = RabbitMQDestroyLogicConfig.class, initConfigClass = RabbitMQDestroyInitConfig.class)
@ComponentResult(name = "true")
public class RabbitMQDestroyComponent extends AbstractComponent<RabbitMQDestroyInitConfig, RabbitMQDestroyLogicConfig> {

    public RabbitMQDestroyComponent() {
        super(RabbitMQDestroyInitConfig.class, RabbitMQDestroyLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        RabbitMQHelper.destroy(getScopeKey());
        return ComponentDataResult.success(true);
    }
}
