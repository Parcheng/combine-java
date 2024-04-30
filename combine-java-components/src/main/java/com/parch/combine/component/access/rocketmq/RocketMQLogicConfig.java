package com.parch.combine.component.access.rocketmq;

import com.parch.combine.core.base.LogicConfig;


public class RocketMQLogicConfig extends LogicConfig {

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
