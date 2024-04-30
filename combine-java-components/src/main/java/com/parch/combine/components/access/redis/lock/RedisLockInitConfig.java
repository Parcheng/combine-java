package com.parch.combine.components.access.redis.lock;

import com.parch.combine.components.access.redis.RedisInitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

public class RedisLockInitConfig extends RedisInitConfig {

    @ComponentField(key = "autoUnLock", name = "是否在流程结束时自动释放锁", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean autoUnLock;

    public Boolean getAutoUnLock() {
        return autoUnLock;
    }

    public void setAutoUnLock(Boolean autoUnLock) {
        this.autoUnLock = autoUnLock;
    }

    @Override
    public void init() {
        super.init();
        if (getAutoUnLock() == null) {
            setAutoUnLock(true);
        }
    }
}
