package com.parch.combine.rocketmq.base;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;


public interface RocketMQLogicConfig extends ILogicConfig {

    @Field(key = "topic", name = "消息Topic", type = FieldTypeEnum.TEXT, isRequired = true)
    String topic();
}
