package com.parch.combine.component.access.rocketmq.product;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.component.access.rocketmq.AbsRocketMQComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
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
public class RocketMQProductComponent extends AbsRocketMQComponent<RocketMQProductInitConfig, RocketMQProductLogicConfig> {

    public RocketMQProductComponent() {
        super(RocketMQProductInitConfig.class, RocketMQProductLogicConfig.class);
    }

    @Override
    public List<String> initConfig() {
        List<String> errorMsg = new ArrayList<>();
        RocketMQProductInitConfig initConfig = getInitConfig();
        RocketMQProductLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getProducerGroup() == null) {
            logicConfig.setProducerGroup(logicConfig.getId());
        }
        if (logicConfig.getTags() == null) {
            logicConfig.setTags(CheckEmptyUtil.EMPTY);
        }

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
