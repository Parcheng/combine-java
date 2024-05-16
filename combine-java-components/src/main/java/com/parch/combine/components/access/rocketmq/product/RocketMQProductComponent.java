package com.parch.combine.components.access.rocketmq.product;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.components.access.rocketmq.AbsRocketMQComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis锁组件
 */
@Component(key = "rocketmq.product", name = "RocketMQ生产者组件", logicConfigClass = RocketMQProductLogicConfig.class, initConfigClass = RocketMQProductInitConfig.class)
@ComponentResult(name = "Message ID")
public class RocketMQProductComponent extends AbsRocketMQComponent<RocketMQProductInitConfig, RocketMQProductLogicConfig> {

    public RocketMQProductComponent() {
        super(RocketMQProductInitConfig.class, RocketMQProductLogicConfig.class);
    }

    @Override
    public List<String> initConfig() {
        List<String> errorMsg = new ArrayList<>();
        RocketMQProductLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getContent() == null) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "消息内容为空"));
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        RocketMQProductInitConfig initConfig = getInitConfig();
        RocketMQProductLogicConfig logicConfig = getLogicConfig();

        DefaultMQProducer producer = new DefaultMQProducer(logicConfig.getProducerGroup());
        producer.setNamesrvAddr(initConfig.getService());

        Object topic = DataVariableHelper.parseValue(logicConfig.getTopic(), false);
        Object tags = DataVariableHelper.parseValue(logicConfig.getTags(), false);
        Object content = DataVariableHelper.parse(logicConfig.getContent());
        String contentStr = content == null ? CheckEmptyUtil.EMPTY : JsonUtil.serialize(content);

        String msgId;
        try {
            producer.start();
            Message message = new Message(topic.toString(), tags.toString(), contentStr.getBytes());
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
