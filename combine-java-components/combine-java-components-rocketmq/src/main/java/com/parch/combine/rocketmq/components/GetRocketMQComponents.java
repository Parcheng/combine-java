package com.parch.combine.rocketmq.components;

import com.parch.combine.core.component.spi.AbsGetComponents;

public class GetRocketMQComponents extends AbsGetComponents {

    public GetRocketMQComponents() {
        super("rocketmq", "RocketMQ", GetRocketMQComponents.class);
    }
}
