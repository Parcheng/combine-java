package com.parch.combine.rocketmq.components;

import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.vo.FlowResult;
import com.parch.combine.rocketmq.base.AbstractRocketMQComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.settings.annotations.ComponentResultDesc;

import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.rocketmq.base.consumer.RocketMQConsumerErrorEnum;
import com.parch.combine.rocketmq.base.consumer.RocketMQConsumerInitConfig;
import com.parch.combine.rocketmq.base.consumer.RocketMQConsumerLogicConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import java.util.HashMap;
import java.util.Map;

@Component(key = "consumer", name = "RocketMQ消费者组件", logicConfigClass = RocketMQConsumerLogicConfig.class, initConfigClass = RocketMQConsumerInitConfig.class)
@ComponentDesc("依赖 rocketmq-client，推荐版本 4.9.4")
@ComponentResult(name = "异常信息或 true")
@ComponentResultDesc("消息数据格式：{ msgId: ‘消息ID’, body: ‘消息内容’ }")
public class RocketMQConsumerComponent extends AbstractRocketMQComponent<RocketMQConsumerInitConfig, RocketMQConsumerLogicConfig> {

    public RocketMQConsumerComponent() {
        super(RocketMQConsumerInitConfig.class, RocketMQConsumerLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        RocketMQConsumerInitConfig initConfig = getInitConfig();
        RocketMQConsumerLogicConfig logicConfig = getLogicConfig();

        String consumerGroup = logicConfig.consumerGroup();
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup == null ? logicConfig.id() : consumerGroup);
        consumer.setNamesrvAddr(initConfig.service());

        try {
            String listenFlowKey = logicConfig.listenFlowKey();
            String finalListenFlowKey = listenFlowKey == null ? (logicConfig.id() + "-Listen") : listenFlowKey;

            String topic = logicConfig.topic();
            String expression = logicConfig.expression();
            consumer.subscribe(topic, expression);
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                for (MessageExt msg : msgs) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("msgId", msg.getMsgId());
                    data.put("body", JsonUtil.string2Obj(new String(msg.getBody()), HashMap.class));

                    FlowResult result = SubComponentTool.execute(manager, finalListenFlowKey, data, logicConfig.components());
                    if (!result.getSuccess()) {
                        PrintErrorHelper.print(this, "消息消费失败, id=" + msg.getMsgId() + " topic=" + topic
                                + " expression=" + expression + " error=" + result.getErrMsg(), null);
                    }
                }
                
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (MQClientException e) {
            PrintErrorHelper.print(RocketMQConsumerErrorEnum.FAIL, e);
            return ComponentDataResult.fail(RocketMQConsumerErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
