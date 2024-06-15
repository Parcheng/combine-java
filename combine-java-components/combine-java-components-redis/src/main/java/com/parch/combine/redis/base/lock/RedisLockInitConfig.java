package com.parch.combine.redis.base.lock;

import com.parch.combine.redis.base.RedisInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface RedisLockInitConfig extends RedisInitConfig {

    @Field(key = "autoUnLock", name = "是否在流程结束时自动释放锁", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean autoUnLock();
}
