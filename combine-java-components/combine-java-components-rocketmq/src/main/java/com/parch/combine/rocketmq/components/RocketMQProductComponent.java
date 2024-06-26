package com.parch.combine.rocketmq.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.rocketmq.base.AbstractRocketMQComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.rocketmq.base.product.RocketMQProductErrorEnum;
import com.parch.combine.rocketmq.base.product.RocketMQProductInitConfig;
import com.parch.combine.rocketmq.base.product.RocketMQProductLogicConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

@Component(key = "product", name = "RocketMQ生产者组件", logicConfigClass = RocketMQProductLogicConfig.class, initConfigClass = RocketMQProductInitConfig.class)
@ComponentDesc("依赖 rocketmq-client，推荐版本 4.9.4")
@ComponentResult(name = "Message ID")
public class RocketMQProductComponent extends AbstractRocketMQComponent<RocketMQProductInitConfig, RocketMQProductLogicConfig> {

    public RocketMQProductComponent() {
        super(RocketMQProductInitConfig.class, RocketMQProductLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        RocketMQProductInitConfig initConfig = getInitConfig();
        RocketMQProductLogicConfig logicConfig = getLogicConfig();

        String producerGroup = logicConfig.producerGroup();
        producerGroup = producerGroup == null ? logicConfig.id() : producerGroup;

        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(initConfig.service());

        String topic = logicConfig.topic();
        String tags = logicConfig.tags();
        Object content = logicConfig.content();
        String contentStr = content == null ? CheckEmptyUtil.EMPTY : JsonUtil.serialize(content);

        String msgId;
        try {
            producer.start();
            Message message = new Message(topic, tags, contentStr.getBytes());
            SendResult result = producer.send(message);
            msgId = result.getMsgId();
        } catch (MQClientException | InterruptedException | RemotingException | MQBrokerException e) {
            ComponentErrorHandler.print(RocketMQProductErrorEnum.FAIL, e);
            return DataResult.fail(RocketMQProductErrorEnum.FAIL);
        } finally {
            producer.shutdown();
        }

        return DataResult.success(msgId);
    }



}
