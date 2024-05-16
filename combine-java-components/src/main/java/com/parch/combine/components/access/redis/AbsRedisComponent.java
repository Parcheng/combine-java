package com.parch.combine.components.access.redis;

import com.parch.combine.core.common.canstant.SymbolConstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * Redis连接处理器
 */
public abstract class AbsRedisComponent<T extends RedisInitConfig, R extends LogicConfig> extends AbsComponent<T, R> {

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

    @Override
    public List<String> init() {
        RedisInitConfig initConfig = getInitConfig();
        this.initRedisConfig();

        // 检查初始化配置
        List<String> checkMsg = new ArrayList<>();
        if (CheckEmptyUtil.isAnyEmpty(initConfig.getPassword())) {
            checkMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "Redis连接配置信息缺失"));
        }
        if (CheckEmptyUtil.isEmpty(initConfig.getNodes())) {
            checkMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "Redis无节点配置"));
        }
        for (String node : initConfig.getNodes()) {
            String[] nodeConfig = node.split(SymbolConstant.COLON);
            if (nodeConfig.length < 2 || !DataTypeIsUtil.isInteger(nodeConfig[1])) {
                checkMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "Redis节点配置缺失"));
            }
        }

        checkMsg.addAll(checkRedisConfig());
        return checkMsg;
    }

    protected abstract void initRedisConfig();

    protected abstract List<String> checkRedisConfig();

    /**
     * 初始化连接
     *
     * @param initConfig 初始化配置
     * @return 结果
     */
    protected synchronized JedisCluster initConn(RedisInitConfig initConfig) {
        if (POOL_MAP.containsKey(initConfig.getId())) {
            return POOL_MAP.get(initConfig.getId());
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(initConfig.getPool().getMax());
        // 最大空闲连接数
        poolConfig.setMaxIdle(initConfig.getPool().getMax() - initConfig.getPool().getMin());
        // 获取连接最大等待时间 ms default -1
        poolConfig.setMaxWaitMillis(initConfig.getPool().getTimeout());

        // 集群节点配置
        Set<HostAndPort> clusterNodes = new HashSet<>();
        for (String node : initConfig.getNodes()) {
            String[] nodeConfig = node.split(SymbolConstant.COLON);
            clusterNodes.add(new HostAndPort(nodeConfig[0], Integer.parseInt(nodeConfig[1])));
        }

        JedisCluster jedisCluster = new JedisCluster(clusterNodes, initConfig.getTimeout(), initConfig.getTimeout(), initConfig.getMaxAttempts(), initConfig.getPassword(), poolConfig);
        POOL_MAP.put(initConfig.getId(), jedisCluster);
        return jedisCluster;
    }

    /**
     * 连接
     *
     * @param initConfig 初始化配置
     * @return 连接
     */
    protected JedisCluster getConn(RedisInitConfig initConfig) {
        if (POOL_MAP.containsKey(initConfig.getId())) {
            return POOL_MAP.get(initConfig.getId());
        }

        return this.initConn(initConfig);
    }
}
