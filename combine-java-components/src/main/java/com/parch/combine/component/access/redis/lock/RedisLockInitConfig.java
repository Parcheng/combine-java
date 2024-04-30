package com.parch.combine.component.access.redis.lock;

import com.parch.combine.component.access.redis.RedisInitConfig;

public class RedisLockInitConfig extends RedisInitConfig {

    private Boolean autoUnLock;

    public Boolean getAutoUnLock() {
        return autoUnLock;
    }

    public void setAutoUnLock(Boolean autoUnLock) {
        this.autoUnLock = autoUnLock;
    }
}
