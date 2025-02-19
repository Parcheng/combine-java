package com.parch.combine.rabbitmq.base.helper;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface RabbitMQQueueConfig {

    @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    String key();

    @Field(key = "exchangeName", name = "队列绑定的交换机名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String exchangeName();

    @Field(key = "exchangeConfig", name = "队列绑定的交换配置", type = FieldTypeEnum.CONFIG)
    @FieldObject(ExchangeConfig.class)
    ExchangeConfig exchangeConfig();

    @Field(key = "queueName", name = "队列名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String queueName();

    @Field(key = "autoAck", name = "消费自动确认", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean autoAck();

    @Field(key = "expireTime", name = "队列消息有效时间（毫秒）", type = FieldTypeEnum.NUMBER)
    Long expireTime();

    @Field(key = "deadQueueName", name = "死信队列名称", type = FieldTypeEnum.TEXT)
    String deadQueueName();

    @Field(key = "durable", name = "是否持久化", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean durable();

    @Field(key = "routeKey", name = "队列的路由KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("默认取队列名称")
    String routeKey();

    @Field(key = "useShareChannel", name = "是否使用共享通道", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean useShareChannel();
}
