package com.parch.combine.components.access.rocketmq.product;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.access.rocketmq.RocketMQLogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

public class RocketMQProductLogicConfig extends RocketMQLogicConfig {

    @ComponentField(key = "tags", name = "标签", type = FieldTypeEnum.TEXT)
    private String tags;

    @ComponentField(key = "producerGroup", name = "生产者组", type = FieldTypeEnum.TEXT, defaultValue = "逻辑配置ID")
    private String producerGroup;

    @ComponentField(key = "content", name = "消息内容", type = FieldTypeEnum.OBJECT, isRequired = true)
    private Object content;

    @Override
    public void init() {
        super.init();
        if (getProducerGroup() == null) {
            setProducerGroup(getId());
        }
        if (getTags() == null) {
            setTags(CheckEmptyUtil.EMPTY);
        }
    }

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
