package com.parch.combine.rabbitmq.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

public class GetRabbitMQComponents extends AbstractGetComponents {

    public GetRabbitMQComponents() {
        super("rabbitmq", "RabbitMQ", GetRabbitMQComponents.class);
    }
}
