package com.parch.combine.components.access.rocketmq.consumer;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.components.access.rocketmq.AbsRocketMQComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.settings.annotations.ComponentResultDesc;
import com.parch.combine.core.tools.SubComponentHelper;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redis锁组件
 */
@Component(key = "rocketmq.consumer", name = "RocketMQ消费者组件", logicConfigClass = RocketMQConsumerLogicConfig.class, initConfigClass = RocketMQConsumerInitConfig.class)
@ComponentResult(name = "异常信息或 true")
@ComponentResultDesc("消息数据格式：{ msgId: ‘消息ID’, body: ‘消息内容’ }")
public class RocketMQConsumerComponent extends AbsRocketMQComponent<RocketMQConsumerInitConfig, RocketMQConsumerLogicConfig> {

    public RocketMQConsumerComponent() {
        super(RocketMQConsumerInitConfig.class, RocketMQConsumerLogicConfig.class);
    }

    @Override
    public List<String> initConfig() {
        List<String> errorMsg = new ArrayList<>();
        RocketMQConsumerLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getComponents())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "处理消息的组件集合为空"));
        }

        // 初始化逻辑中使用的组件
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getComponents())) {
            List<String> initErrorMsgs = SubComponentHelper.init(manager, logicConfig.getComponents());
            for (String initErrorMsg : initErrorMsgs) {
                errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, initErrorMsg));
            }
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        RocketMQConsumerInitConfig initConfig = getInitConfig();
        RocketMQConsumerLogicConfig logicConfig = getLogicConfig();

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(logicConfig.getConsumerGroup());
        consumer.setNamesrvAddr(initConfig.getService());


        Object topic = DataVariableHelper.parseValue(logicConfig.getTopic(), false);
        Object expression = DataVariableHelper.parseValue(logicConfig.getExpression(), false);

        try {
            String listenFlowKey = logicConfig.getListenFlowKey();
            consumer.subscribe(topic.toString(), expression.toString());
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                for (MessageExt msg : msgs) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("msgId", msg.getMsgId());
                    data.put("body", JsonUtil.deserialize(new String(msg.getBody()), HashMap.class));

                    DataResult result = SubComponentHelper.execute(manager, listenFlowKey, data, logicConfig.getComponents());
                    if (!result.getSuccess()) {
                        ComponentErrorHandler.print(this, "消息消费失败, id=" + msg.getMsgId() + " topic=" + topic.toString()
                                + " expression=" + expression.toString() + " error=" + result.getErrMsg(), null);
                    }
                }
                
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (MQClientException e) {
            ComponentErrorHandler.print(RocketMQConsumerErrorEnum.FAIL, e);
            return DataResult.fail(RocketMQConsumerErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
