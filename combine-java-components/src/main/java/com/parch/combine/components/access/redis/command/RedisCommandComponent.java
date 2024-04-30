package com.parch.combine.components.access.redis.command;

import com.parch.combine.common.constant.SymbolConstant;
import com.parch.combine.common.util.*;
import com.parch.combine.components.access.redis.AbsRedisComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.variable.ArrayGetHelper;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import java.util.*;

/**
 * Redis命令组件
 */
@Component(key = "redis.command", name = "Redis命令组件", logicConfigClass = RedisCommandLogicConfig.class, initConfigClass = RedisCommandInitConfig.class)
@ComponentResult(name = "命令的执行结果集合")
public class RedisCommandComponent extends AbsRedisComponent<RedisCommandInitConfig, RedisCommandLogicConfig> {

    public RedisCommandComponent() {
        super(RedisCommandInitConfig.class, RedisCommandLogicConfig.class);
    }

    @Override
    public void initRedisConfig() {}

    @Override
    public List<String> checkRedisConfig() {
        List<String> checkMsg = new ArrayList<>();
        RedisCommandLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getCommands())) {
            checkMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "无可执行命令"));
        }
        for (int i = 0; i < logicConfig.getCommands().size(); i++) {
            RedisCommandLogicConfig.RedisCommand item = logicConfig.getCommands().get(i);
            String baseMsg = "第<" + (i+1) + ">条-";
            RedisCommandTypeEnum type = item.getType();
            if (type == RedisCommandTypeEnum.NONE) {
                checkMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "不支持的命令类型"));
            }
            if (item.getParams() == null || item.getParams().length < type.getMinParamCount()) {
                checkMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "参数数量不合规"));
            }
        }

        return checkMsg;
    }

    @Override
    public DataResult execute() {
        RedisCommandLogicConfig logicConfig = getLogicConfig();
        List<Object> result = new ArrayList<>();
        try (JedisCluster cluster = this.getConn(getInitConfig())) {
            for (RedisCommandLogicConfig.RedisCommand command : logicConfig.getCommands()) {
                Object itemResult = null;
                try {
                    itemResult = this.executeCommand(cluster, command);
                } catch (Exception e) {
                    ComponentErrorHandler.print(RedisCommandErrorEnum.UNKNOWN_ERROR, e);
                    if (logicConfig.getFailStop()) {
                        return DataResult.fail(result, RedisCommandErrorEnum.UNKNOWN_ERROR);
                    }
                }
                result.add(itemResult);
            }
        }

        return DataResult.success(result);
    }

    @SuppressWarnings("unchecked")
    private RedisCommandResult executeCommand(JedisCluster cluster, RedisCommandLogicConfig.RedisCommand command) {
        RedisCommandTypeEnum type = command.getType();
        Object[] params = this.parseParams(command.getParams());

        String key = ArrayGetHelper.getString(params, 0);
        if (CheckEmptyUtil.isEmpty(key)) {
            return RedisCommandResult.fail("KEY参数为空");
        }

        switch (type.getType()) {
            case "STRING":
                switch (type) {
                    case GET:
                        return RedisCommandResult.success(cluster.get(key));
                    case SET:
                        boolean useSetParams = false;
                        SetParams setParams = new SetParams();
                        String nxAndXx = ArrayGetHelper.getString(params, 1, null);
                        Long ex = ArrayGetHelper.getLong(params, 2, null);
                        if (nxAndXx != null) {
                            if ("nx".equalsIgnoreCase(nxAndXx)) {
                                useSetParams = true;
                                setParams.nx();
                            } else if ("xx".equalsIgnoreCase(nxAndXx)){
                                useSetParams = true;
                                setParams.xx();
                            }
                        }
                        if (ex != null) {
                            useSetParams = true;
                            setParams.ex(ex);
                        }

                        if (useSetParams) {
                            return RedisCommandResult.success(cluster.set(key, ArrayGetHelper.getString(params, 3), setParams));
                        } else {
                            return RedisCommandResult.success(cluster.set(key, ArrayGetHelper.getString(params, 3)));
                        }
                    case APPEND:
                        return RedisCommandResult.success(cluster.append(key, ArrayGetHelper.getString(params, 1, CheckEmptyUtil.EMPTY)));
                    case INCR:
                        return RedisCommandResult.success(cluster.incr(key));
                    case DECR:
                        return RedisCommandResult.success(cluster.decr(key));
                }
                break;
            case "HASH":
                switch (type) {
                    case HGET:
                        return RedisCommandResult.success(cluster.hget(key, ArrayGetHelper.getString(params, 1)));
                    case HSET:
                        String nxAndXx = ArrayGetHelper.getString(params, 1);
                        if (params[2] instanceof Map) {
                            Map<String, String> hashValue = new HashMap<>();
                            ((Map<String, Object>) params[2]).forEach((k, v) -> hashValue.put(k, DataParseUtil.getString(v, CheckEmptyUtil.EMPTY)));
                            if ("nx".equalsIgnoreCase(nxAndXx)) {
                                return RedisCommandResult.success(cluster.hset(key, hashValue));
                            } else {
                                return RedisCommandResult.success(cluster.hset(key, hashValue));
                            }
                        } else {
                            String[] keyValue = ArrayGetHelper.getString(params, 2).split(SymbolConstant.COLON);
                            if ("nx".equalsIgnoreCase(nxAndXx)) {
                                return RedisCommandResult.success(cluster.hsetnx(key, keyValue[0], ArrayGetHelper.getString(keyValue, 1, CheckEmptyUtil.EMPTY)));
                            } else {
                                return RedisCommandResult.success(cluster.hset(key, keyValue[0], ArrayGetHelper.getString(keyValue, 1, CheckEmptyUtil.EMPTY)));
                            }
                        }
                    case HDEL:
                        return RedisCommandResult.success(cluster.hdel(key, ArrayGetHelper.splitToString(params, 1, params.length)));
                }
                break;
            case "LIST":
                switch (type) {
                    case LSET:
                        Integer index = ArrayGetHelper.getInteger(params, 1, null);
                        if (index == null) {
                            return RedisCommandResult.fail("LIST命令索引参数为空");
                        }
                        return RedisCommandResult.success(cluster.lset(key, index, ArrayGetHelper.getString(params, 2)));
                    case RPUSH:
                        return RedisCommandResult.success(cluster.rpush(key, ArrayGetHelper.splitToString(params, 1, params.length)));
                    case LPUSH:
                        return RedisCommandResult.success(cluster.lpush(key, ArrayGetHelper.splitToString(params, 1, params.length)));
                    case RPOP:
                        return RedisCommandResult.success(cluster.rpop(key, ArrayGetHelper.getInteger(params, 1, 1)));
                    case LPOP:
                        return RedisCommandResult.success(cluster.lpop(key, ArrayGetHelper.getInteger(params, 1, 1)));
                    case LLEN:
                        return RedisCommandResult.success(cluster.llen(key));
                    case LRANGE:
                        int start = ArrayGetHelper.getInteger(params, 1, 0);
                        int end = ArrayGetHelper.getInteger(params, 2, start + 1);
                        return RedisCommandResult.success(cluster.lrange(key, start, end));
                    case LINDEX:
                        return RedisCommandResult.success(cluster.lindex(key, ArrayGetHelper.getInteger(params, 1, 0)));
                    case LREM:
                        return RedisCommandResult.success(cluster.lrem(key, ArrayGetHelper.getInteger(params, 1, 1), ArrayGetHelper.getString(params, 2)));
                }
                break;
            case "SET":
                switch (type) {
                    case SADD:
                        return RedisCommandResult.success(cluster.sadd(key, ArrayGetHelper.splitToString(params, 1, params.length)));
                    case SMEMBERS:
                        return RedisCommandResult.success(cluster.smembers(key));
                    case SISMEMBER:
                        return RedisCommandResult.success(cluster.sismember(key, ArrayGetHelper.getString(params, 1)));
                    case SREM:
                        return RedisCommandResult.success(cluster.srem(key, ArrayGetHelper.splitToString(params, 1, params.length)));
                    case SCARD:
                        return RedisCommandResult.success(cluster.scard(key));
                    case SRANDMEMBER:
                        if (params.length > 1) {
                            return RedisCommandResult.success(cluster.srandmember(key, ArrayGetHelper.getInteger(params, 1, 1)));
                        } else {
                            return RedisCommandResult.success(cluster.srandmember(key));
                        }
                    case SPOP:
                        if (params.length > 1) {
                            return RedisCommandResult.success(cluster.spop(key, ArrayGetHelper.getInteger(params, 1, 1)));
                        } else {
                            return RedisCommandResult.success(cluster.spop(key));
                        }
                    case SMOVE:
                        return RedisCommandResult.success(cluster.smove(key, ArrayGetHelper.getString(params, 1), ArrayGetHelper.getString(params, 2)));
                    case SDIFF:
                        return RedisCommandResult.success(cluster.sdiff(ArrayGetHelper.splitToString(params, 0, params.length)));
                    case SUNION:
                        return RedisCommandResult.success(cluster.sunion(ArrayGetHelper.splitToString(params, 0, params.length)));
                    case SINTER:
                        return RedisCommandResult.success(cluster.sinter(ArrayGetHelper.splitToString(params, 0, params.length)));
                }
                break;
            case "ZSET":
                switch (type) {
                    case ZADD:
                        return RedisCommandResult.success(cluster.zadd(key, ArrayGetHelper.getDouble(params, 1, 0D), ArrayGetHelper.getString(params, 2)));
                    case ZCARD:
                        return RedisCommandResult.success(cluster.zcard(key));
                    case ZRANGE:
                        long start = ArrayGetHelper.getLong(params, 1, 0L);
                        long end = ArrayGetHelper.getLong(params, 2, start + 1);
                        return RedisCommandResult.success(cluster.zrange(key, start, end));
                    case ZREVRANGE:
                        start = ArrayGetHelper.getLong(params, 1, 0L);
                        end = ArrayGetHelper.getLong(params, 2, start + 1);
                        return RedisCommandResult.success(cluster.zrevrange(key, start, end));
                    case ZRANGEBYSCORE:
                        Double min = ArrayGetHelper.getDouble(params, 1, null);
                        Double max = ArrayGetHelper.getDouble(params, 2, null);
                        if (min == null || max == null) {
                            return RedisCommandResult.fail("ZSET命令最大/最小分数参数为空");
                        }
                        return RedisCommandResult.success(cluster.zrangeByScore(key, min, max));
                    case ZSCORE:
                        return RedisCommandResult.success(cluster.zscore(key, ArrayGetHelper.getString(params, 1)));
                    case ZREM:
                        return RedisCommandResult.success(cluster.zrem(key, ArrayGetHelper.splitToString(params, 1, params.length)));
                    case ZINCRBY:
                        return RedisCommandResult.success(cluster.zincrby(key, ArrayGetHelper.getDouble(params, 1, 0D), ArrayGetHelper.getString(params, 2)));
                    case ZCOUNT:
                        min = ArrayGetHelper.getDouble(params, 1, null);
                        max = ArrayGetHelper.getDouble(params, 2, null);
                        if (min == null || max == null) {
                            return RedisCommandResult.fail("ZSET命令最大/最小分数参数为空");
                        }
                        return RedisCommandResult.success(cluster.zcount(key, min, max));
                    case ZMAXPOP:
                        return RedisCommandResult.success(cluster.zpopmax(key, ArrayGetHelper.getInteger(params, 1, 1)));
                    case ZMINPOP:
                        return RedisCommandResult.success(cluster.zpopmin(key, ArrayGetHelper.getInteger(params, 1, 1)));
                    case ZRANK:
                        return RedisCommandResult.success(cluster.zrank(key, ArrayGetHelper.getString(params, 1)));
                    case ZREVRANK:
                        return RedisCommandResult.success(cluster.zrevrank(key, ArrayGetHelper.getString(params, 1)));
                }
                break;
            case "KEY":
                switch (type) {
                    case EXPIRE:
                        Long ex = ArrayGetHelper.getLong(params, 1, null);
                        if (ex == null) {
                            return RedisCommandResult.fail("设置的有效期为空");
                        }
                        return RedisCommandResult.success(cluster.pexpire(key, ex));
                    case DEL:
                        return RedisCommandResult.success(cluster.del(key));
                }
                break;
        }

        return RedisCommandResult.fail("未知的命令类型");
    }

    private Object[] parseParams(String[] params) {
        Object[] result = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            result[i] = DataVariableHelper.parseValue(params[i], false);
        }
        return result;
    }
}
