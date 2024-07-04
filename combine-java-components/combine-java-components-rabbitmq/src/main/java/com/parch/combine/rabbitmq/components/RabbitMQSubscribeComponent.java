package com.parch.combine.rabbitmq.components;

import com.parch.combine.rabbitmq.base.AbstractRabbitMQComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.rabbitmq.base.subscribe.RabbitMQSubscribeErrorEnum;
import com.parch.combine.rabbitmq.base.subscribe.RabbitMQSubscribeInitConfig;
import com.parch.combine.rabbitmq.base.subscribe.RabbitMQSubscribeLogicConfig;
import com.parch.combine.rabbitmq.base.helper.RabbitMQHelper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component(key = "subscribe", name = "RabbitMQ订阅组件", logicConfigClass = RabbitMQSubscribeLogicConfig.class, initConfigClass = RabbitMQSubscribeInitConfig.class)
@ComponentDesc("依赖 amqp-client，推荐版本 5.13.1")
@ComponentResult(name = "订阅KEY（可以通过该KEY取消订阅）")
public class RabbitMQSubscribeComponent extends AbstractRabbitMQComponent<RabbitMQSubscribeInitConfig, RabbitMQSubscribeLogicConfig> {

    public RabbitMQSubscribeComponent() {
        super(RabbitMQSubscribeInitConfig.class, RabbitMQSubscribeLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        RabbitMQSubscribeInitConfig initConfig = getInitConfig();
        RabbitMQSubscribeLogicConfig logicConfig = getLogicConfig();

        Connection conn = RabbitMQHelper.getConnection(initConfig.mq());
        Channel channel = RabbitMQHelper.getChannel(conn, initConfig.queue());

        String listenFlowKey = logicConfig.listenFlowKey();
        String finalListenFlowKey = listenFlowKey == null ? (logicConfig.id() + "-Listen") : listenFlowKey;

        String consumerTag = RabbitMQHelper.subscribe(channel, initConfig.queue(), logicConfig.count(), data -> {
            DataResult result = SubComponentTool.execute(manager, finalListenFlowKey, data, logicConfig.components());
            if (!result.getSuccess()) {
                ComponentErrorHandler.print(this, "消息消费失败, queue=" + initConfig.queue().queueName() + " error=" + result.getErrMsg(), null);
                return false;
            }

            return true;
        });

        if (consumerTag == null) {
            return DataResult.fail(RabbitMQSubscribeErrorEnum.FAIL);
        }

        return DataResult.success(consumerTag);
    }
}
