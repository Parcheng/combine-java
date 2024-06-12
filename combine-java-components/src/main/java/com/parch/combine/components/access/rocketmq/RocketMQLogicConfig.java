package com.parch.combine.components.access.rocketmq;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;


public interface RocketMQLogicConfig extends ILogicConfig {

    @Field(key = "topic", name = "消息Topic", type = FieldTypeEnum.TEXT, isRequired = true)
    String topic();
}
