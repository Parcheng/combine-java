package com.parch.combine.component.access.rocketmq.consumer;

import com.parch.combine.component.access.rocketmq.RocketMQLogicConfig;

import java.util.List;

public class RocketMQConsumerLogicConfig extends RocketMQLogicConfig {

    private String listenFlowKey;

    private String consumerGroup;

    private String expression;

    private List<Object> components;

    public String getListenFlowKey() {
        return listenFlowKey;
    }

    public void setListenFlowKey(String listenFlowKey) {
        this.listenFlowKey = listenFlowKey;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Object> getComponents() {
        return components;
    }

    public void setComponents(List<Object> components) {
        this.components = components;
    }
}
