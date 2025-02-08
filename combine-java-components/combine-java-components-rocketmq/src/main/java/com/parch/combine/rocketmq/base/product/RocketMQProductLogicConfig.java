package com.parch.combine.rocketmq.base.product;

import com.parch.combine.rocketmq.base.RocketMQLogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface RocketMQProductLogicConfig extends RocketMQLogicConfig {

    @Field(key = "tags", name = "标签", type = FieldTypeEnum.TEXT)
    String tags();

    @Field(key = "producerGroup", name = "生产者组", type = FieldTypeEnum.TEXT, defaultValue = "逻辑配置ID")
    String producerGroup();

    @Field(key = "content", name = "消息内容", type = FieldTypeEnum.ANY, isRequired = true)
    Object content();
}
