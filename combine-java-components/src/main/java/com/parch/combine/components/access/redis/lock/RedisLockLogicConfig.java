package com.parch.combine.components.access.redis.lock;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

public class RedisLockLogicConfig extends LogicConfig {

    @ComponentField(key = "count", name = "锁状态：正数表示加锁，否则解锁", type = FieldTypeEnum.NUMBER, isRequired = true)
    @ComponentFieldEg(eg = "1", desc = "加锁")
    @ComponentFieldEg(eg = "-1", desc = "解锁")
    private Integer count;

    @ComponentField(key = "key", name = "锁的KEY", type = FieldTypeEnum.TEXT, defaultValue = "随机生成")
    @ComponentFieldEg(eg = "user_12345", desc = "锁的 KEY 为 user_12345")
    private String key;

    @ComponentField(key = "value", name = "锁的值", type = FieldTypeEnum.TEXT)
    @ComponentFieldDesc("如果不为空，则解锁时必须与加锁时的 KEY 和 VALUE 都一致才能成功解锁")
    @ComponentFieldEg(eg = "ORDER_NO_123445", desc = "锁的值为 ORDER_NO_123445")
    private String value;

    @ComponentField(key = "keyPrefix", name = "", type = FieldTypeEnum.TEXT)
    @ComponentFieldEg(eg = "order_", desc = "锁的 KEY 会在前面拼接 order_")
    private String keyPrefix;

    @ComponentField(key = "expire", name = "锁有效期（毫秒）", type = FieldTypeEnum.NUMBER, defaultValue = "锁不会过期")
    @ComponentFieldEg(eg = "10000", desc = "有效期为 10000 毫秒")
    private Long expire;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
