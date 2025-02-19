package com.parch.combine.redis.base.lock;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface RedisLockLogicConfig extends ILogicConfig {

    @Field(key = "count", name = "锁状态：正数表示加锁，否则解锁", type = FieldTypeEnum.NUMBER, isRequired = true)
    @FieldEg(eg = "1", desc = "加锁")
    @FieldEg(eg = "-1", desc = "解锁")
    Integer count();

    @Field(key = "key", name = "锁的KEY", type = FieldTypeEnum.TEXT, defaultValue = "随机生成")
    @FieldEg(eg = "user_12345", desc = "锁的 KEY 为 user_12345")
    String key();

    @Field(key = "value", name = "锁的值", type = FieldTypeEnum.TEXT)
    @FieldDesc("如果不为空，则解锁时必须与加锁时的 KEY 和 VALUE 都一致才能成功解锁")
    @FieldEg(eg = "ORDER_NO_123445", desc = "锁的值为 ORDER_NO_123445")
    String value();

    @Field(key = "keyPrefix", name = "", type = FieldTypeEnum.TEXT)
    @FieldEg(eg = "order_", desc = "锁的 KEY 会在前面拼接 order_")
    String keyPrefix();

    @Field(key = "expire", name = "锁有效期（毫秒）", type = FieldTypeEnum.NUMBER, defaultValue = "锁不会过期")
    @FieldEg(eg = "10000", desc = "有效期为 10000 毫秒")
    Long expire();
}
