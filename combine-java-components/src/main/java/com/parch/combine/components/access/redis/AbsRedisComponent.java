package com.parch.combine.components.access.redis;

import com.parch.combine.core.common.canstant.SymbolConstant;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.ILogicConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * Redis连接处理器
 */
public abstract class AbsRedisComponent<T extends RedisInitConfig, R extends ILogicConfig> extends AbsComponent<T, R> {

    private static Map<String, JedisCluster> POOL_MAP = new HashMap<>();

    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public AbsRedisComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    /**
     * 初始化连接
     *
     * @param initConfig 初始化配置
     * @return 结果
     */
    protected synchronized JedisCluster initConn(RedisInitConfig initConfig) {
        String id = initConfig.id();
        if (POOL_MAP.containsKey(id)) {
            return POOL_MAP.get(id);
        }

        RedisInitConfig.Pool pool = initConfig.pool();
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(pool.max());
        // 最大空闲连接数
        poolConfig.setMaxIdle(pool.max() - pool.min());
        // 获取连接最大等待时间 ms default -1
        poolConfig.setMaxWaitMillis(pool.timeout());

        // 集群节点配置
        Set<HostAndPort> clusterNodes = new HashSet<>();
        for (String node : initConfig.nodes()) {
            String[] nodeConfig = node.split(SymbolConstant.COLON);
            clusterNodes.add(new HostAndPort(nodeConfig[0], Integer.parseInt(nodeConfig[1])));
        }

        JedisCluster jedisCluster = new JedisCluster(clusterNodes, initConfig.timeout(), initConfig.timeout(), initConfig.maxAttempts(), initConfig.password(), poolConfig);
        POOL_MAP.put(id, jedisCluster);
        return jedisCluster;
    }

    /**
     * 连接
     *
     * @param initConfig 初始化配置
     * @return 连接
     */
    protected JedisCluster getConn(RedisInitConfig initConfig) {
        String id = initConfig.id();
        if (POOL_MAP.containsKey(id)) {
            return POOL_MAP.get(id);
        }

        return this.initConn(initConfig);
    }
}
