package com.parch.combine.rabbitmq.components;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.rabbitmq.base.AbstractRabbitMQComponent;
import com.parch.combine.rabbitmq.base.cancel.RabbitMQCancelErrorEnum;
import com.parch.combine.rabbitmq.base.cancel.RabbitMQCancelInitConfig;
import com.parch.combine.rabbitmq.base.cancel.RabbitMQCancelLogicConfig;
import com.parch.combine.rabbitmq.base.helper.RabbitMQHelper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component(key = "cancel", name = "RabbitMQ取消订阅组件", logicConfigClass = RabbitMQCancelLogicConfig.class, initConfigClass = RabbitMQCancelInitConfig.class)
@ComponentDesc("依赖 amqp-client，推荐版本 5.13.1")
@ComponentResult(name = "true 或异常信息")
public class RabbitMQCancelComponent extends AbstractRabbitMQComponent<RabbitMQCancelInitConfig, RabbitMQCancelLogicConfig> {

    public RabbitMQCancelComponent() {
        super(RabbitMQCancelInitConfig.class, RabbitMQCancelLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        RabbitMQCancelInitConfig initConfig = getInitConfig();
        RabbitMQCancelLogicConfig logicConfig = getLogicConfig();

        Connection conn = RabbitMQHelper.getConnection(initConfig.mq());
        Channel channel = RabbitMQHelper.getChannel(conn, initConfig.queue());

        boolean success = RabbitMQHelper.unSubscribe(channel, logicConfig.key());
        if (!success) {
            return ComponentDataResult.fail(RabbitMQCancelErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
