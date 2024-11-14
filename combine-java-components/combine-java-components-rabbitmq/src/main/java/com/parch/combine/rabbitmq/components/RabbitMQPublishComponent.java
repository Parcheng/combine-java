package com.parch.combine.rabbitmq.components;

import com.parch.combine.rabbitmq.base.AbstractRabbitMQComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.rabbitmq.base.helper.RabbitMQHelper;
import com.parch.combine.rabbitmq.base.publish.RabbitMQPublishErrorEnum;
import com.parch.combine.rabbitmq.base.publish.RabbitMQPublishInitConfig;
import com.parch.combine.rabbitmq.base.publish.RabbitMQPublishLogicConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component(key = "publish", name = "RabbitMQ发布组件", logicConfigClass = RabbitMQPublishLogicConfig.class, initConfigClass = RabbitMQPublishInitConfig.class)
@ComponentDesc("依赖 amqp-client，推荐版本 5.13.1")
@ComponentResult(name = "Message ID")
public class RabbitMQPublishComponent extends AbstractRabbitMQComponent<RabbitMQPublishInitConfig, RabbitMQPublishLogicConfig> {

    public RabbitMQPublishComponent() {
        super(RabbitMQPublishInitConfig.class, RabbitMQPublishLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        RabbitMQPublishInitConfig initConfig = getInitConfig();
        RabbitMQPublishLogicConfig logicConfig = getLogicConfig();

        Connection conn = RabbitMQHelper.getConnection(getScopeKey(), initConfig.mq());
        Channel channel = RabbitMQHelper.getChannel(getScopeKey(), conn, initConfig.queue(), false);

        boolean success = RabbitMQHelper.publish(getScopeKey(), channel, initConfig.queue(), logicConfig.content(), logicConfig.confirm());
        if (!success) {
            return ComponentDataResult.fail(RabbitMQPublishErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
