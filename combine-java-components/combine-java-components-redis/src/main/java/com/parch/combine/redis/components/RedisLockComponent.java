package com.parch.combine.redis.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.redis.base.AbstractRedisComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.redis.base.lock.RedisLockInitConfig;
import com.parch.combine.redis.base.lock.RedisLockLogicConfig;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import java.util.Collection;
import java.util.UUID;

@Component(key = "redis.lock", name = "Redis分布式锁组件", logicConfigClass = RedisLockLogicConfig.class, initConfigClass = RedisLockInitConfig.class)
@ComponentDesc({"简单版分布式锁，暂时不支持“未获取到锁时自旋等待功能”、“流程未执行完锁自动续期功能”", "依赖 jedis，推荐版本 3.6.3"})
@ComponentResult(name = "是否成功获取锁：true | false")
public class RedisLockComponent extends AbstractRedisComponent<RedisLockInitConfig, RedisLockLogicConfig> {

    private static final String LOCK_VALUE_FILED = "$REDIS_LOCK";

    public RedisLockComponent() {
        super(RedisLockInitConfig.class, RedisLockLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        RedisLockLogicConfig logicConfig = getLogicConfig();

        boolean result;
        String key = getKey();
        String value = logicConfig.value();
        try (JedisCluster cluster = this.getConn(getInitConfig())) {
            if (logicConfig.count() > 0) {
                result = lock(cluster, key, value);
            } else {
                unlock(cluster, key, value);
                result = true;
            }
        }

        return ComponentDataResult.success(result);
    }

    public String getKey() {
        RedisLockLogicConfig logicConfig = getLogicConfig();
        String key = CheckEmptyUtil.EMPTY;
        String keyPrefix = logicConfig.keyPrefix();
        key = keyPrefix == null ? key : (keyPrefix + key);
        if (CheckEmptyUtil.isEmpty(key)) {
            key = UUID.randomUUID().toString();
        }
        return key;
    }

    public boolean lock(JedisCluster cluster, String key, String value) {
        RedisLockInitConfig initConfig = getInitConfig();
        RedisLockLogicConfig logicConfig = getLogicConfig();

        SetParams setParams = new SetParams();
        setParams.nx();

        Long expire = logicConfig.expire();
        if (expire != null) {
            setParams.ex(expire);
        }
        value = value == null ? UUID.randomUUID().toString() : value;

        if (initConfig.autoUnLock()) {
            ComponentContextHandler.addRuntimeData(LOCK_VALUE_FILED, new String[]{key, value});
        }

        return cluster.set(key, value, setParams) != null;
    }

    public void unlock(JedisCluster cluster, String key, Object valueObj) {
        if (valueObj != null) {
            String value = cluster.get(key);
            if (!valueObj.toString().equals(value)) {
                return;
            }
        }

        cluster.del(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean end() {
        Object runtimeData = ComponentContextHandler.getRuntimeData(LOCK_VALUE_FILED);
        if (runtimeData == null) {
            return true;
        }

        try (JedisCluster cluster = this.getConn(getInitConfig())) {
            for (Object item : (Collection<Object>) runtimeData) {
                if (!(item instanceof String[])) {
                    continue;
                }

                String[] keyValue = (String[]) item;
                if (keyValue.length > 1 && keyValue[0] != null && keyValue[1] != null) {
                    unlock(cluster, keyValue[0], keyValue[1]);
                }
            }
        }

        return true;
    }
}
