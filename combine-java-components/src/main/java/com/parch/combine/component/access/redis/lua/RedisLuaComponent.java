package com.parch.combine.component.access.redis.lua;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.DataParseUtil;
import com.parch.combine.common.util.StringUtil;
import com.parch.combine.component.access.redis.AbsRedisComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis锁组件
 */
public class RedisLuaComponent extends AbsRedisComponent<RedisLuaInitConfig, RedisLuaLogicConfig> {

    public RedisLuaComponent() {
        super(RedisLuaInitConfig.class, RedisLuaLogicConfig.class);
    }

    @Override
    public void initRedisConfig() {
    }

    @Override
    public List<String> checkRedisConfig() {
        List<String> checkMsg = new ArrayList<>();
        RedisLuaLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getScriptLines())) {
            checkMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "脚本代码为空"));
        }

        return checkMsg;
    }

    @Override
    public DataResult execute() {
        RedisLuaLogicConfig logicConfig = getLogicConfig();

        List<String> keys = parseParams(logicConfig.getKeys());
        List<String> args = parseParams(logicConfig.getArgs());
        String scriptCode = StringUtil.join(logicConfig.getScriptLines(), "\n");
        try (JedisCluster cluster = this.getConn(getInitConfig())) {
            return DataResult.success(cluster.eval(scriptCode, keys, args));
        }
    }

    public List<String> parseParams(List<String> params) {
        if (params == null) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>(params.size());
        for (String item : params) {
            Object finalValue = DataVariableHelper.parseValue(item, false);
            result.add(DataParseUtil.getString(finalValue, CheckEmptyUtil.EMPTY));
        }
        return result;
    }
}
