package com.parch.combine.components.access.redis.lua;

import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.components.access.redis.AbsRedisComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.vo.DataResult;
import redis.clients.jedis.JedisCluster;
import java.util.Arrays;
import java.util.List;

/**
 * Redis锁组件
 */
@Component(key = "redis.lua", name = "Redis脚本组件", logicConfigClass = RedisLuaLogicConfig.class, initConfigClass = RedisLuaInitConfig.class)
@ComponentDesc("依赖 jedis，推荐版本 3.6.3")
@ComponentResult(name = "脚本执行结果")
public class RedisLuaComponent extends AbsRedisComponent<RedisLuaInitConfig, RedisLuaLogicConfig> {

    public RedisLuaComponent() {
        super(RedisLuaInitConfig.class, RedisLuaLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        RedisLuaLogicConfig logicConfig = getLogicConfig();

        List<String> keys = Arrays.asList(logicConfig.keys());
        List<String> args = Arrays.asList(logicConfig.args());
        String scriptCode = StringUtil.join(logicConfig.scriptLines(), "\n");
        try (JedisCluster cluster = this.getConn(getInitConfig())) {
            return DataResult.success(cluster.eval(scriptCode, keys, args));
        }
    }
}
