package com.parch.combine.redis.components;

import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.redis.base.AbstractRedisComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.redis.base.lua.RedisLuaInitConfig;
import com.parch.combine.redis.base.lua.RedisLuaLogicConfig;
import redis.clients.jedis.JedisCluster;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component(key = "redis.lua", name = "Redis脚本组件", logicConfigClass = RedisLuaLogicConfig.class, initConfigClass = RedisLuaInitConfig.class)
@ComponentDesc("依赖 jedis，推荐版本 3.6.3")
@ComponentResult(name = "脚本执行结果")
public class RedisLuaComponent extends AbstractRedisComponent<RedisLuaInitConfig, RedisLuaLogicConfig> {

    private static Map<String, String> PRECOMPILE_MAP = null;

    public RedisLuaComponent() {
        super(RedisLuaInitConfig.class, RedisLuaLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        RedisLuaLogicConfig logicConfig = getLogicConfig();

        List<String> keys = Arrays.asList(logicConfig.keys());
        List<String> args = Arrays.asList(logicConfig.args());
        String scriptCode = StringUtil.join(logicConfig.scriptLines(), "\n");
        try (JedisCluster cluster = this.getConn(getInitConfig())) {
            if (logicConfig.hasPrecompile()) {
                String scriptKey = logicConfig.precompileKey();
                if (scriptKey == null) {
                    scriptKey = scriptCode;
                }

                Map<String, String> precompileMap = getPrecompileMap();
                String scriptSha = precompileMap.get(scriptKey);
                if (scriptSha == null) {
                    scriptSha = cluster.scriptLoad(scriptCode, "lua");
                    precompileMap.put(scriptKey, scriptSha);
                }

                return ComponentDataResult.success(cluster.evalsha(scriptSha, keys, args));
            } else {
                return ComponentDataResult.success(cluster.eval(scriptCode, keys, args));
            }
        }
    }

    public static Map<String, String> getPrecompileMap() {
        if (PRECOMPILE_MAP != null) {
            return PRECOMPILE_MAP;
        }

        synchronized (RedisLuaComponent.class) {
            if (PRECOMPILE_MAP != null) {
                return PRECOMPILE_MAP;
            }

            PRECOMPILE_MAP = new ConcurrentHashMap<>(16);
            return PRECOMPILE_MAP;
        }
    }
}
