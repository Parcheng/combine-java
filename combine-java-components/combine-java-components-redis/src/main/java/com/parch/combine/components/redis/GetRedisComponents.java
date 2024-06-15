package com.parch.combine.components.redis;

import com.parch.combine.core.component.spi.AbsGetComponents;

public class GetRedisComponents extends AbsGetComponents {

    public GetRedisComponents() {
        super("redis", " Redis相关组件", GetRedisComponents.class);
    }
}
