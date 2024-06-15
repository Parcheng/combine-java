package com.parch.combine.redis.components;

import com.parch.combine.core.component.spi.AbsGetComponents;

public class GetRedisComponents extends AbsGetComponents {

    public GetRedisComponents() {
        super("redis", " Redis", GetRedisComponents.class);
    }
}
