package com.parch.combine.components.access.rocketmq;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;


public class RocketMQLogicConfig extends LogicConfig {

    @ComponentField(key = "topic", name = "消息Topic", type = FieldTypeEnum.TEXT, isRequired = true)
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
