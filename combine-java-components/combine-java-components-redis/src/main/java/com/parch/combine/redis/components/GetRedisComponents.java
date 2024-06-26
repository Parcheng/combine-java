package com.parch.combine.redis.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

public class GetRedisComponents extends AbstractGetComponents {

    public GetRedisComponents() {
        super("redis", " Redis", GetRedisComponents.class);
    }
}
