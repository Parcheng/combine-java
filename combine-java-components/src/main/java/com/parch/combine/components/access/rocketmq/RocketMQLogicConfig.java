package com.parch.combine.components.access.rocketmq;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;


public abstract class RocketMQLogicConfig extends LogicConfig {

    @Field(key = "topic", name = "消息Topic", type = FieldTypeEnum.TEXT, hasExpression = true, isRequired = true)
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
