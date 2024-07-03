package com.parch.combine.rabbitmq.base.cancel;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.rabbitmq.base.RabbitMQLogicConfig;

public interface RabbitMQCancelLogicConfig extends RabbitMQLogicConfig {

    @Field(key = "key", name = "订阅KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("订阅时生成的KEY")
    String key();
}
