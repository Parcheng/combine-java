package com.parch.combine.components.access.rocketmq.product;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.access.rocketmq.RocketMQLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class RocketMQProductLogicConfig extends RocketMQLogicConfig {

    @Field(key = "tags", name = "标签", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION})
    private String tags;

    @Field(key = "producerGroup", name = "生产者组", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, defaultValue = "逻辑配置ID")
    private String producerGroup;

    @Field(key = "content", name = "消息内容", type = {FieldTypeEnum.OBJECT, FieldTypeEnum.EXPRESSION}, isRequired = true)
    private Object content;

    @Override
    public void init() {
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
