package com.parch.combine.components.access.redis.lua;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.components.access.redis.AbsRedisComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis锁组件
 */
@Component(key = "redis.lua", name = "Redis脚本组件", logicConfigClass = RedisLuaLogicConfig.class, initConfigClass = RedisLuaInitConfig.class)
@ComponentResult(name = "脚本执行结果")
public class RedisLuaComponent extends AbsRedisComponent<RedisLuaInitConfig, RedisLuaLogicConfig> {

    public RedisLuaComponent() {
        super(RedisLuaInitConfig.class, RedisLuaLogicConfig.class);
    }

    @Override
    public void initRedisConfig() {}

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
