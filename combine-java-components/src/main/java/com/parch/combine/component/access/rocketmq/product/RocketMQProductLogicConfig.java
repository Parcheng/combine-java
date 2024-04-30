package com.parch.combine.component.access.rocketmq.product;

import com.parch.combine.component.access.rocketmq.RocketMQLogicConfig;

public class RocketMQProductLogicConfig extends RocketMQLogicConfig {

    private String tags;

    private String producerGroup;

    private Object content;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
