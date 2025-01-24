package com.parch.combine.rabbitmq.base;

import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.rabbitmq.base.helper.RabbitMQConfig;
import com.parch.combine.rabbitmq.base.helper.RabbitMQQueueConfig;

public interface RabbitMQInitConfig extends IInitConfig {

    @Field(key = "mq", name = "MQ配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(RabbitMQConfig.class)
    RabbitMQConfig mq();

    @Field(key = "queue", name = "队列配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(RabbitMQQueueConfig.class)
    RabbitMQQueueConfig queue();
}
